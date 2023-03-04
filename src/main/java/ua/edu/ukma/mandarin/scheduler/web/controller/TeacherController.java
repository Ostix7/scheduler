package ua.edu.ukma.mandarin.scheduler.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Teacher;
import ua.edu.ukma.mandarin.scheduler.service.TeacherService;

@RestController
@RequestMapping(path = "api/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping()
    @ResponseBody
    public Iterable<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }




}
