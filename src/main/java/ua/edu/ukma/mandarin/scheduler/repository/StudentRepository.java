package ua.edu.ukma.mandarin.scheduler.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {

    Student getByUserId(int id);


}
