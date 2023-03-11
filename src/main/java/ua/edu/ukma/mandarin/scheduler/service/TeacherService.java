package ua.edu.ukma.mandarin.scheduler.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Teacher;
import ua.edu.ukma.mandarin.scheduler.exception.TeacherNotFoundException;
import ua.edu.ukma.mandarin.scheduler.repository.TeacherRepository;

@Transactional
@Service
@RequiredArgsConstructor
public class TeacherService {

  private final TeacherRepository teacherRepository;
  private static final Logger logger = LogManager.getLogger();
  static final Marker MARKER_TEACHER = MarkerManager.getMarker("TeacherService");

  public Teacher getTeacher(Long id) {
    Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

    if (optionalTeacher.isEmpty()) throw new TeacherNotFoundException();

    return optionalTeacher.get();
  }

  public void deleteTeacher(Teacher teacher) {
    teacherRepository.delete(teacher);
  }

  public Iterable<Teacher> getAllTeachers() {
    return teacherRepository.findAll();
  }

  public long countTeachers() {
    return teacherRepository.count();
  }

  public Teacher addTeacher(Teacher teacher) {
    Teacher t = teacherRepository.save(teacher);
    //    ThreadContext.put(
    //        "username", t.getUser().getFirstName() + " " + teacher.getUser().getLastName());
    //    ThreadContext.put("ID", t.getTeacherId().toString());
    //    logger.info(MARKER_TEACHER, "Create teacher");
    //    ThreadContext.clearMap();
    return t;
  }
}
