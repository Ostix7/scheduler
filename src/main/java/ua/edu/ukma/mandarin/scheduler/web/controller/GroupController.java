package ua.edu.ukma.mandarin.scheduler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.mandarin.scheduler.domain.dto.GroupDTO;
import ua.edu.ukma.mandarin.scheduler.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<GroupDTO> getAllGroupsForLecturer(@Param("lecturer") Long lecturerId) {
        return groupService.findAllGroupsForLecturerId(lecturerId);
    }

    @DeleteMapping("/{id}")
    public void deleteSubjectById(@PathVariable Long id) {
        groupService.deleteGroupById(id);
    }
}
