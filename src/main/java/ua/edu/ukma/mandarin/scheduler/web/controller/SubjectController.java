package ua.edu.ukma.mandarin.scheduler.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.mandarin.scheduler.domain.dto.SubjectDTO;
import ua.edu.ukma.mandarin.scheduler.service.SubjectService;

@RestController
@RequestMapping("/api/subject")
@RequiredArgsConstructor
public class SubjectController {

  private final SubjectService subjectService;

  @GetMapping("/all")
  public List<SubjectDTO> getAllSubjects() {
    return subjectService.getAllSubjects();
  }

  @GetMapping(value = "/all", params = "author")
  public List<SubjectDTO> getAllSubjectsByAuthor(@RequestParam(name = "author") Long authorId) {
    return subjectService.getAllSubjectsByAuthorId(authorId);
  }

  @GetMapping("/{id}")
  public SubjectDTO getSubjectById(@PathVariable Long id) {
    return subjectService.getSubjectById(id);
  }

  @GetMapping
  public SubjectDTO getSubjectByName(@RequestParam(name = "name") String name) {
    return subjectService.getSubjectBySubjectName(name);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void addSubject(@RequestBody SubjectDTO newSubject) {
    subjectService.addNewSubject(newSubject);
  }

  @PutMapping
  public void updateSubject(@RequestBody SubjectDTO updatedSubject) {
    subjectService.updateSubject(updatedSubject);
  }

  @DeleteMapping("/{id}")
  public void deleteSubjectById(@PathVariable Long id) {
    subjectService.deleteSubjectById(id);
  }

  @PutMapping("/register/{id}")
  public void registerStudentToGroup(
      @RequestParam(name = "student") Long studentId, @PathVariable Long id) {
    subjectService.registerToSubject(studentId, id);
  }

  @PutMapping("/unregister/{id}")
  public void unregisterStudentFromGroup(
      @RequestParam(name = "student") Long studentId, @PathVariable Long id) {
    subjectService.unregisterFromSubject(studentId, id);
  }
}
