package ua.edu.ukma.mandarin.scheduler.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.ukma.mandarin.scheduler.domain.dto.ScheduleDTO;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Schedule;
import ua.edu.ukma.mandarin.scheduler.repository.SchedulerRepository;

@Service
@RequiredArgsConstructor
public class SchedulerService {
  private final SchedulerRepository schedulerRepository;

  public Schedule findById(long id) {
    return schedulerRepository.findById(id).orElse(null);
  }

  public List<ScheduleDTO> findAll() {
    return schedulerRepository.findAll().stream()
        .map(
            x ->
                new ScheduleDTO(
                    x.getId(),
                    x.getGroup().getId(),
                    x.getDay().toString(),
                    x.getStartTime(),
                    x.getEndTime()))
        .collect(Collectors.toList());
  }

  public List<ScheduleDTO> findByStartDate(String startTime) {
    return schedulerRepository.findAllByStartTime(startTime).stream()
        .map(
            x ->
                new ScheduleDTO(
                    x.getId(), x.getGroupId(), x.getDay(), x.getStartTime(), x.getEndTime()))
        .collect(Collectors.toList());
  }

  public void deleteById(long id) {
    schedulerRepository.deleteById(id);
  }
}
