package ua.edu.ukma.mandarin.scheduler.web.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponse {

  private Integer status;
  private String message;
}
