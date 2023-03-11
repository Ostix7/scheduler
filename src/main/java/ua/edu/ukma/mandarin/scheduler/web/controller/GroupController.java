package ua.edu.ukma.mandarin.scheduler.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.mandarin.scheduler.domain.dto.GroupDTO;
import ua.edu.ukma.mandarin.scheduler.service.GroupService;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {

  private final GroupService groupService;

  @GetMapping
  public List<GroupDTO> getAllGroupsForTeacher(@RequestParam(name = "teacher") Long teacherId) {
    return groupService.findAllGroupsForTeacherId(teacherId);
  }

  @DeleteMapping("/{id}")
  public void deleteSubjectById(@PathVariable Long id) {
    groupService.deleteGroupById(id);
  }

  @PutMapping("/register/{id}")
  public void registerStudentToGroup(
      @RequestParam(name = "studentId") Long studentId, @PathVariable Long id) {
    groupService.registerToGroup(studentId, id);
  }

  @PutMapping("/unregister/{id}")
  public void unregisterStudentFromGroup(
      @RequestParam(name = "studentId") Long studentId, @PathVariable Long id) {
    groupService.unregisterFromGroup(studentId, id);
  }
}
