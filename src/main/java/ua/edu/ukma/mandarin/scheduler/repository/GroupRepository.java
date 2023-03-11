package ua.edu.ukma.mandarin.scheduler.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
  List<Group> findAllByTeacherId(Long teacherId);
}
