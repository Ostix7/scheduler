package ua.edu.ukma.mandarin.scheduler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.mandarin.scheduler.domain.dto.GroupDTO;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Schedule;
import ua.edu.ukma.mandarin.scheduler.service.SchedulerService;

import java.util.List;

@RestController
@RequestMapping(path = "api/schedule")
public class ScheduleController {

    private final SchedulerService schedulerService;

    @Autowired
    public ScheduleController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @GetMapping
    public List<Schedule> getAllSchedules() {
        return schedulerService.findAll();
    }
    @GetMapping
    public List<Schedule> getAllSchedulesByFaculty() {
        return schedulerService.findAll();
    }

    @GetMapping("/{id}")
    public void getScheduleById(@PathVariable Long id) {
        schedulerService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteScheduleById(@PathVariable Long id) {
        schedulerService.deleteById(id);
    }
}
