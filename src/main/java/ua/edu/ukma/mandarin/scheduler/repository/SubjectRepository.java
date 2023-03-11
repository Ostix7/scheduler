package ua.edu.ukma.mandarin.scheduler.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
  Optional<Subject> findSubjectByName(String name);

  List<Subject> findAllByAuthorId(Long authorId);

  //  List<Subject> findAllByStudent(Student student); // TODO: need to write query most probably
}
