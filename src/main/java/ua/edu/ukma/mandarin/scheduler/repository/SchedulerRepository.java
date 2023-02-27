package ua.edu.ukma.mandarin.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Scheduler;

import java.util.List;

public interface SchedulerRepository extends JpaRepository<Scheduler, Long> {
    List<Scheduler> findAllByFaculty();
    List<Scheduler> findAllByCourse();



}
