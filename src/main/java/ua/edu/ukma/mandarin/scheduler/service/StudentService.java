package ua.edu.ukma.mandarin.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.*;
import org.springframework.stereotype.Service;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Student;
import ua.edu.ukma.mandarin.scheduler.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;
  private static final Logger logger = LogManager.getLogger();
  static final Marker MARKER_STUDENT = MarkerManager.getMarker("StudentService");

  public Student createStudent(Student student) {
    Student t = studentRepository.save(student);
    // ThreadContext.put("username", t.getUser().getFirstName() + " " +
    // student.getUser().getLastName());
    // ThreadContext.put("ID", t.getUser().getUserId().toString());
    logger.info(MARKER_STUDENT, "Create student");
    ThreadContext.clearMap();
    return t;
  }

  public void deleteStudent(Long id) {
    studentRepository.deleteById(id);
  }

  public Iterable<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  public Student addStudent(Student student) {
    return studentRepository.save(student);
  }

  public Student getStudentById(Long id) {
    return studentRepository.findById(id).orElse(null);
  }
}
