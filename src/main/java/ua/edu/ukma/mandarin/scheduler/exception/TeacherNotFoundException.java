package ua.edu.ukma.mandarin.scheduler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "teacher not found")
public class TeacherNotFoundException extends RuntimeException{
}
