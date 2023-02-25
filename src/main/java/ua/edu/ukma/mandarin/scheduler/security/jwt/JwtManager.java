package ua.edu.ukma.mandarin.scheduler.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Principal;

@Component
public class JwtManager {

  private static final long ACCESS_TOKEN_EXPIRATION_TIME_MILLIS = 60 * 60 * 1000; // 1 hour
  private static final String CLAIM_ROLES = "roles";
  private static final String ISSUER = "Scheduler";

  private final JwtProperties jwtProperties;
  private final Algorithm algorithm;
  private final JWTVerifier verifier;

  public JwtManager(final JwtProperties jwtProperties) {
    this.jwtProperties = jwtProperties;
    this.algorithm = Algorithm.HMAC256(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    this.verifier = JWT.require(algorithm).build();
  }

  public String getAccessToken(Principal principal) {
    return JWT.create()
        .withSubject(principal.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME_MILLIS))
        .withIssuer(ISSUER)
        .withClaim(
            CLAIM_ROLES,
            principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
        .sign(algorithm);
  }

  public DecodedJWT verifyToken(String token) {
    return verifier.verify(token);
  }

  public String getEmail(String token) {
    return verifyToken(token).getSubject();
  }

  public List<String> getRoles(String token) {
    return verifyToken(token).getClaim(CLAIM_ROLES).asList(String.class);
  }

  public boolean isTokenExpired(String token) {
    final Date now = new Date();
    return verifyToken(token).getExpiresAt().after(now);
  }
}
