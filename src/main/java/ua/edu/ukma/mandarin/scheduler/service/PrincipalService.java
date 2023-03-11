package ua.edu.ukma.mandarin.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.ukma.mandarin.scheduler.domain.entity.security.Principal;
import ua.edu.ukma.mandarin.scheduler.repository.PrincipalRepository;

@Service
@RequiredArgsConstructor
public class PrincipalService {

  private final PrincipalRepository principalRepository;

  public Principal getPrincipalByEmail(String email) {
    return principalRepository
        .findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Principal with provided email does not exist"));
  }
}
