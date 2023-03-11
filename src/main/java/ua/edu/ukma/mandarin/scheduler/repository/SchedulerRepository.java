package ua.edu.ukma.mandarin.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.ukma.mandarin.scheduler.domain.dto.ScheduleDTO;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Schedule;

import java.util.List;

public interface SchedulerRepository extends JpaRepository<Schedule, Long> {
    List<ScheduleDTO> findAllByStartTime(long startTime);

}
