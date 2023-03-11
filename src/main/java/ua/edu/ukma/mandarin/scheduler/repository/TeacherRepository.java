package ua.edu.ukma.mandarin.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
  Teacher getByPrincipalId(Long id);
}
