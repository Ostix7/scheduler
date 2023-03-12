package ua.edu.ukma.mandarin.scheduler.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ua.edu.ukma.mandarin.scheduler.domain.dto.GroupDTO;
import ua.edu.ukma.mandarin.scheduler.domain.dto.SubjectDTO;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Group;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Subject;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Teacher;
import ua.edu.ukma.mandarin.scheduler.repository.SubjectRepository;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final GroupService groupService;

    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(
                        x ->
                                new SubjectDTO(
                                        x.getId(), x.getName(), x.getAuthor().getId(), getGroupDTOS(x.getGroups())))
                .collect(Collectors.toList());
    }

    public List<SubjectDTO> getAllSubjectsByAuthorId(Long authorId) {
        return subjectRepository.findAllByAuthorId(authorId).stream()
                .map(
                        x ->
                                new SubjectDTO(
                                        x.getId(), x.getName(), x.getAuthor().getId(), getGroupDTOS(x.getGroups())))
                .collect(Collectors.toList());
    }

    public SubjectDTO getSubjectById(Long id) {
        return subjectRepository
                .findById(id)
                .map(
                        subject ->
                                new SubjectDTO(
                                        subject.getId(),
                                        subject.getName(),
                                        subject.getAuthor().getId(),
                                        getGroupDTOS(subject.getGroups())))
                .orElse(null);
    }

    public SubjectDTO getSubjectBySubjectName(String name) {
        return subjectRepository
                .findSubjectByName(name)
                .map(
                        subject ->
                                new SubjectDTO(
                                        subject.getId(),
                                        subject.getName(),
                                        subject.getAuthor().getId(),
                                        getGroupDTOS(subject.getGroups())))
                .orElse(null);
    }

    public void addNewSubject(SubjectDTO subjectDTO) {
        Teacher author = groupService.getTeacher(subjectDTO.getAuthorId());
        Subject subject =
                Subject.builder().id(subjectDTO.getId()).name(subjectDTO.getName()).author(author).build();
        Subject subjectSaved = subjectRepository.save(subject);
        if (!CollectionUtils.isEmpty(subjectDTO.getGroups())) {
            groupService.addGroupsForSubject(subjectDTO.getGroups(), subjectSaved);
        }
    }

    public void updateSubject(SubjectDTO subjectDTO) {
        subjectRepository.findById(subjectDTO.getId()).ifPresent(subject -> {
            Teacher author = groupService.getTeacher(subjectDTO.getAuthorId());
            Subject updatedSubject =
                    Subject.builder().id(subjectDTO.getId()).name(subjectDTO.getName()).author(author).build();
            Subject subjectSaved = subjectRepository.save(updatedSubject);
            groupService.updateGroupsForSubject(subjectDTO.getGroups(), subjectSaved);
        });
    }

    @Transactional
    public void deleteSubjectById(Long id) {
        subjectRepository.findById(id).ifPresent(subject -> {
            groupService.deleteAllGroupsForSubject(subject);
            subjectRepository.deleteById(id);
        });
    }

    private List<GroupDTO> getGroupDTOS(List<Group> groups) {
        return Optional.ofNullable(groups)
                .map(
                        g ->
                                g.stream()
                                        .map(
                                                group ->
                                                        new GroupDTO(
                                                                group.getId(), group.getNumber(), group.getTeacher().getId()))
                                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public void registerToSubject(Long studentId, long subjectId) {
        subjectRepository
                .findById(subjectId)
                .ifPresent(
                        subject -> {
                            subject.getStudents().add(groupService.getStudent(studentId));
                            subjectRepository.save(subject);
                        });
    }

    public void unregisterFromSubject(Long studentId, long subjectId) {
        subjectRepository
                .findById(subjectId)
                .ifPresent(
                        subject -> {
                            subject.getStudents().remove(groupService.getStudent(studentId));
                            subjectRepository.save(subject);
                        });
    }
}
