package ua.edu.ukma.mandarin.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

  Student getByPrincipalId(Long id);
}
