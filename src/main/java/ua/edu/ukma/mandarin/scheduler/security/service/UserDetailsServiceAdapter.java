package ua.edu.ukma.mandarin.scheduler.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.edu.ukma.mandarin.scheduler.service.PrincipalService;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceAdapter implements UserDetailsService {

  private final PrincipalService principalService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return principalService.getPrincipalByEmail(username);
  }
}
