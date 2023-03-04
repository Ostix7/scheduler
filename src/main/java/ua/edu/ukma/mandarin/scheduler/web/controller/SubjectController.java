package ua.edu.ukma.mandarin.scheduler.web.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.mandarin.scheduler.domain.dto.SubjectDTO;
import ua.edu.ukma.mandarin.scheduler.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/all")
    public List<SubjectDTO> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/all")
    public List<SubjectDTO> getAllSubjects(@Param("author") Long authorId) {
        return subjectService.getAllSubjectsByAuthorId(authorId);
    }

    @GetMapping("/{id}")
    public SubjectDTO getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }

    @GetMapping
    public SubjectDTO getSubjectById(@Param("name") String name) {
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
}
