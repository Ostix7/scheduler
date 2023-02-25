package ua.edu.ukma.mandarin.scheduler.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.ukma.mandarin.scheduler.domain.dto.jwt.JwtToken;
import ua.edu.ukma.mandarin.scheduler.domain.dto.principal.LoginPrincipalDTO;
import ua.edu.ukma.mandarin.scheduler.domain.dto.principal.RegisterPrincipalDTO;
import ua.edu.ukma.mandarin.scheduler.service.AuthenticationService;
import ua.edu.ukma.mandarin.scheduler.web.response.ErrorResponse;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<?> loginPrincipal(@RequestBody LoginPrincipalDTO loginPrincipalDTO) {
    try {
      JwtToken token = authenticationService.loginPrincipal(loginPrincipalDTO);
      return ResponseEntity.ok(token);
    } catch (Exception ex) {
      return ResponseEntity.badRequest()
          .body(
              ErrorResponse.builder()
                  .status(HttpStatus.BAD_REQUEST.value())
                  .message(ex.getMessage())
                  .build());
    }
  }

  @PostMapping("/registration")
  public ResponseEntity<?> registerPrincipal(
      @RequestBody RegisterPrincipalDTO registerPrincipalDTO) {
    authenticationService.registerPrincipal(registerPrincipalDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
