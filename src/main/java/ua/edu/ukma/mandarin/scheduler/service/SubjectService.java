package ua.edu.ukma.mandarin.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.mandarin.scheduler.domain.dto.GroupDTO;
import ua.edu.ukma.mandarin.scheduler.domain.dto.SubjectDTO;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Group;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Subject;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Teacher;
import ua.edu.ukma.mandarin.scheduler.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final GroupService groupService;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, GroupService groupService) {
        this.subjectRepository = subjectRepository;
        this.groupService = groupService;
    }

    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(x -> new SubjectDTO(x.getId(), x.getName(), x.getAuthor().getTeacherId(),
                        getGroupDTOS(x.getGroups())))
                .collect(Collectors.toList());
    }

    public List<SubjectDTO> getAllSubjectsByAuthorId(long authorId) {
        return subjectRepository.findAllByAuthorId(authorId)
                .stream()
                .map(x -> new SubjectDTO(x.getId(), x.getName(), x.getAuthor().getTeacherId(),
                        getGroupDTOS(x.getGroups())))
                .collect(Collectors.toList());
    }

    public SubjectDTO getSubjectById(long id) {
        return subjectRepository.findById(id)
                .map(subject -> new SubjectDTO(subject.getId(), subject.getName(), subject.getAuthor().getTeacherId(),
                        getGroupDTOS(subject.getGroups())))
                .orElse(null);
    }

    public SubjectDTO getSubjectBySubjectName(String name) {
        return subjectRepository.findSubjectByName(name)
                .map(subject -> new SubjectDTO(subject.getId(), subject.getName(), subject.getAuthor().getTeacherId(),
                        getGroupDTOS(subject.getGroups())))
                .orElse(null);
    }

    public void addNewSubject(SubjectDTO subjectDTO) {
        Teacher author = groupService.getTeacher(subjectDTO.getAuthorId());
        Subject subject = Subject.builder()
                .id(subjectDTO.getId())
                .name(subjectDTO.getName())
                .author(author)
                .build();
        Subject subjectSaved = subjectRepository.save(subject);

        groupService.addGroupsForSubject(subjectDTO.getGroupDTOList(), subjectSaved);
    }

    public void updateSubject(SubjectDTO subjectDTO) {
        subjectRepository.findById(subjectDTO.getId())
                .ifPresent(subject -> addNewSubject(subjectDTO));
    }

    public void deleteSubjectById(long id) {
        subjectRepository.findById(id)
                .ifPresent(subject -> subjectRepository.deleteById(id));
    }

    private List<GroupDTO> getGroupDTOS(List<Group> groups) {
        return Optional.ofNullable(groups).map(g -> g.stream()
                .map(group -> new GroupDTO(group.getId(), group.getNumber(), group.getLecturer().getTeacherId()))
                .collect(Collectors.toList()))
                .orElse(null);
    }

    public void registerToSubject(Integer studentId, long subjectId) {
        subjectRepository.findById(subjectId)
                .ifPresent(subject -> {
                    subject.getStudents().add(groupService.getStudent(studentId));
                    subjectRepository.save(subject);
                });
    }

    public void unregisterFromSubject(Integer studentId, long subjectId) {
        subjectRepository.findById(subjectId)
                .ifPresent(subject -> {
                    subject.getStudents().remove(groupService.getStudent(studentId));
                    subjectRepository.save(subject);
                });
    }
}
