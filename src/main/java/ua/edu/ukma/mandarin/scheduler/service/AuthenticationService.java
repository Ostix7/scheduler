package ua.edu.ukma.mandarin.scheduler.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.ukma.mandarin.scheduler.domain.dto.jwt.JwtToken;
import ua.edu.ukma.mandarin.scheduler.domain.dto.principal.ChangePasswordDTO;
import ua.edu.ukma.mandarin.scheduler.domain.dto.principal.LoginPrincipalDTO;
import ua.edu.ukma.mandarin.scheduler.domain.dto.principal.RegisterPrincipalDTO;
import ua.edu.ukma.mandarin.scheduler.domain.entity.security.Principal;
import ua.edu.ukma.mandarin.scheduler.exception.EntityValidationException;
import ua.edu.ukma.mandarin.scheduler.repository.PrincipalRepository;
import ua.edu.ukma.mandarin.scheduler.security.jwt.JwtManager;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final JwtManager jwtManager;
  private final PasswordEncoder passwordEncoder;

  private final PrincipalRepository principalRepository;

  public JwtToken loginPrincipal(LoginPrincipalDTO loginPrincipalDTO) {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            loginPrincipalDTO.getEmail(), loginPrincipalDTO.getPassword());
    Principal principal =
        (Principal) authenticationManager.authenticate(authenticationToken).getPrincipal();

    String accessToken = jwtManager.getAccessToken(principal);
    return new JwtToken(accessToken);
  }

  @Transactional
  public void registerPrincipal(RegisterPrincipalDTO registerPrincipalDTO) {
    if (principalRepository.findByEmail(registerPrincipalDTO.getEmail()).isPresent()) {
      throw new EntityValidationException("Principal with provided email already exists");
    }

    Principal principal =
        Principal.builder()
            .email(registerPrincipalDTO.getEmail())
            .password(passwordEncoder.encode(registerPrincipalDTO.getPassword()))
            .roles(List.of())
            .build();
    principalRepository.save(principal);
  }

  @Transactional
  public void changePassword(ChangePasswordDTO changePasswordDTO) {
    Optional<Principal> principalOpt =
        principalRepository.findById(changePasswordDTO.getPrincipalId());
    if (principalOpt.isEmpty()) {
      throw new EntityValidationException("Principal with provided id does not exist");
    }

    Principal principal = principalOpt.get();
    String encodedOldPassword = passwordEncoder.encode(changePasswordDTO.getOldPassword());
    if (!encodedOldPassword.equals(principal.getPassword())) {
      throw new EntityValidationException(
          "Provided old password does not match with actual old password");
    }

    principal.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
    principalRepository.save(principal);
  }
}
