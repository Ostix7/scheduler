package ua.edu.ukma.mandarin.scheduler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScheduleDTO {

  private Long id;
  private Long groupId;
  private String day;
  private String startTime;
  private String endTime;
}
