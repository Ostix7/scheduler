package ua.edu.ukma.mandarin.scheduler.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.edu.ukma.mandarin.scheduler.web.response.ErrorResponse;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private static final List<String> ignoredPaths =
      List.of("/api/authentication/registration", "/api/authentication/login");
  private static final String BEARER_STR = "Bearer ";
  private static final int BEARER_LENGTH = BEARER_STR.length();

  private final JwtManager jwtManager;
  private final ObjectMapper objectMapper;

  public JwtFilter(final JwtManager jwtManager) {
    this.jwtManager = jwtManager;
    this.objectMapper = new ObjectMapper();
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    final String path = request.getServletPath();
    if (ignoredPaths.contains(path)) {
      filterChain.doFilter(request, response);
    } else {
      Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
          .filter(header -> header.startsWith(BEARER_STR))
          .map(header -> header.substring(BEARER_LENGTH))
          .ifPresentOrElse(
              token -> performAuthentication(token, request, response, filterChain),
              () -> continueFilterChain(request, response, filterChain));
    }
  }

  @SneakyThrows
  private void performAuthentication(
      String token,
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) {
    try {
      String username = jwtManager.getEmail(token);
      Collection<SimpleGrantedAuthority> authorities =
          jwtManager.getRoles(token).stream().map(SimpleGrantedAuthority::new).toList();

      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(username, null, authorities);
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      ErrorResponse errorResponse =
          ErrorResponse.builder()
              .status(HttpStatus.UNAUTHORIZED.value())
              .message(ex.getMessage())
              .build();
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      objectMapper.writeValue(response.getWriter(), errorResponse);
    }
  }

  @SneakyThrows
  private void continueFilterChain(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
    filterChain.doFilter(request, response);
  }
}
