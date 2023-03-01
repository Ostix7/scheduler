package ua.edu.ukma.mandarin.scheduler.web.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Student;
import ua.edu.ukma.mandarin.scheduler.service.StudentService;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping()
    @ResponseBody
    public Iterable<Student> getAllStudents() {
        return studentService.getAllStudents();
    }


    @PostMapping(consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public Student addStudent(@RequestBody @Valid Student student) {
        return studentService.addStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
    }



}
