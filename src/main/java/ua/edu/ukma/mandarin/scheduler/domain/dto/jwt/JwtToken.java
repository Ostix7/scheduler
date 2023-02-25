package ua.edu.ukma.mandarin.scheduler.domain.dto.jwt;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JwtToken {

  String accessToken;
}
