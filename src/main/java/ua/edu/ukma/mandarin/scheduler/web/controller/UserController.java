package ua.edu.ukma.mandarin.scheduler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.mandarin.scheduler.service.AuthenticationService;
import ua.edu.ukma.mandarin.scheduler.service.StudentService;
import ua.edu.ukma.mandarin.scheduler.service.TeacherService;
import ua.edu.ukma.mandarin.scheduler.service.UserService;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/{id}")
    public String getUserPage(@PathVariable("id")Long id, Model model){
        model.addAttribute("user", userService.getUserById(id.intValue()));
        //model.addAttribute("user", new User());
        return "user-page";
    }

    @GetMapping("/{id}/profile")
    public String getUserPageProfile(@PathVariable("id")Long id, Model model){
        model.addAttribute("user", userService.getUserById(id.intValue()));
        return "user-page-profile";
    }

}

