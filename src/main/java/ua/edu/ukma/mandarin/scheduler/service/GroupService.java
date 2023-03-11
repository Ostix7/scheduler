package ua.edu.ukma.mandarin.scheduler.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.mandarin.scheduler.domain.dto.GroupDTO;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Group;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Subject;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Teacher;
import ua.edu.ukma.mandarin.scheduler.repository.GroupRepository;

@Service
public class GroupService {

  private final GroupRepository groupRepository;
  private final TeacherService teacherService;
  private final StudentService studentService;

  @Autowired
  public GroupService(
      GroupRepository groupRepository,
      TeacherService teacherService,
      StudentService studentService) {
    this.groupRepository = groupRepository;
    this.teacherService = teacherService;
    this.studentService = studentService;
  }

  public void addGroupsForSubject(List<GroupDTO> groupDTOList, Subject subject) {
    List<Group> groupsToSave =
        groupDTOList.stream()
            .map(
                groupDTO ->
                    Group.builder()
                        .id(groupDTO.getId())
                        .subject(subject)
                        .number(groupDTO.getNumber())
                        .teacher(getTeacher(groupDTO.getTeacherId()))
                        .build())
            .collect(Collectors.toList());

    groupRepository.saveAll(groupsToSave);
  }

  public Teacher getTeacher(Long id) {
    return teacherService.getTeacher(id);
  }

  public List<GroupDTO> findAllGroupsForTeacherId(Long teacherId) {
    return groupRepository.findAllByTeacherId(teacherId).stream()
        .map(group -> new GroupDTO(group.getId(), group.getNumber(), group.getTeacher().getId()))
        .collect(Collectors.toList());
  }

  public void deleteGroupById(Long id) {
    groupRepository.findById(id).ifPresent(group -> groupRepository.deleteById(id));
  }

  public void registerToGroup(Long studentId, Long groupId) {
    groupRepository
        .findById(groupId)
        .ifPresent(
            group -> {
              group.getStudents().add(studentService.getStudentById(studentId));
              groupRepository.save(group);
            });
  }

  public void unregisterFromGroup(Long studentId, Long groupId) {
    groupRepository
        .findById(groupId)
        .ifPresent(
            group -> {
              group.getStudents().remove(studentService.getStudentById(studentId));
              groupRepository.save(group);
            });
  }
}
