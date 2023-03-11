package ua.edu.ukma.mandarin.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.mandarin.scheduler.domain.dto.GroupDTO;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Group;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Student;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Subject;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Teacher;
import ua.edu.ukma.mandarin.scheduler.repository.GroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @Autowired
    public GroupService(GroupRepository groupRepository, TeacherService teacherService, StudentService studentService) {
        this.groupRepository = groupRepository;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    public void addGroupsForSubject(List<GroupDTO> groupDTOList, Subject subject) {
        List<Group> groupsToSave = groupDTOList.stream()
                .map(groupDTO ->
                    Group.builder()
                            .id(groupDTO.getId())
                            .subject(subject)
                            .number(groupDTO.getNumber())
                            .lecturer(getTeacher(groupDTO.getLecturerId()))
                            .build()
                )
                .collect(Collectors.toList());

        groupRepository.saveAll(groupsToSave);
    }

    public Teacher getTeacher(int id) {
        return teacherService.getTeacher(id);
    }

    public Student getStudent(int id) {
        return studentService.getStudentById(id);
    }

    public List<GroupDTO> findAllGroupsForLecturerId(long lecturerId) {
        return groupRepository.findAllByLecturerId(lecturerId)
                .stream()
                .map(group -> new GroupDTO(group.getId(), group.getNumber(), group.getLecturer().getTeacherId()))
                .collect(Collectors.toList());
    }

    public void deleteGroupById(long id) {
        groupRepository.findById(id)
                .ifPresent(group -> groupRepository.deleteById(id));
    }

    public void registerToGroup(int studentId, long groupId) {
        groupRepository.findById(groupId)
                .ifPresent(group -> {
                    group.getStudents().add(studentService.getStudentById(studentId));
                    groupRepository.save(group);
                });

    }

    public void unregisterFromGroup(int studentId, long groupId) {
        groupRepository.findById(groupId)
                .ifPresent(group -> {
                    group.getStudents().remove(studentService.getStudentById(studentId));
                    groupRepository.save(group);
                });
    }
}
