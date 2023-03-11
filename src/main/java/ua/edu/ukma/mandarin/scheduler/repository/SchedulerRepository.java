package ua.edu.ukma.mandarin.scheduler.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.ukma.mandarin.scheduler.domain.dto.ScheduleDTO;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Schedule;

public interface SchedulerRepository extends JpaRepository<Schedule, Long> {
  List<ScheduleDTO> findAllByStartTime(String startTime);
}
