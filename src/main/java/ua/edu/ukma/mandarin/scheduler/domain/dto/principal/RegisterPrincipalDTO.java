package ua.edu.ukma.mandarin.scheduler.domain.dto.principal;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterPrincipalDTO {

  String email;
  String password;
}
