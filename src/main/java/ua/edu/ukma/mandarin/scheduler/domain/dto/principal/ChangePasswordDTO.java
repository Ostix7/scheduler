package ua.edu.ukma.mandarin.scheduler.domain.dto.principal;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChangePasswordDTO {

  private Long principalId;
  private String oldPassword;
  private String newPassword;
}
