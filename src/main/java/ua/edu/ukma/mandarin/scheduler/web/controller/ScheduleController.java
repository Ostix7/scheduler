package ua.edu.ukma.mandarin.scheduler.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Schedule;
import ua.edu.ukma.mandarin.scheduler.service.SchedulerService;

@RestController
@RequestMapping(path = "/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

  private final SchedulerService schedulerService;

  @GetMapping
  public List<Schedule> getAllSchedules() {
    return schedulerService.findAll();
  }

  @GetMapping("/{id}")
  public void getScheduleById(@PathVariable Long id) {
    schedulerService.findById(id);
  }

  @DeleteMapping("/{id}")
  public void deleteScheduleById(@PathVariable Long id) {
    schedulerService.deleteById(id);
  }
}
