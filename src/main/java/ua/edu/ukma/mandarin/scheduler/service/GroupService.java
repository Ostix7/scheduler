package ua.edu.ukma.mandarin.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.mandarin.scheduler.domain.dto.GroupDTO;
import ua.edu.ukma.mandarin.scheduler.domain.dto.SubjectDTO;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Group;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Subject;
import ua.edu.ukma.mandarin.scheduler.repository.GroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void addGroupsForSubject(List<GroupDTO> groupDTOList, Subject subject) {
        List<Group> groupsToSave = groupDTOList.stream()
                .map(groupDTO -> Group.builder()
                        .id(groupDTO.getId())
                        .subject(subject)
                        .number(groupDTO.getNumber())
                        .lecturerId(groupDTO.getLecturerId())
                        .build())
                .collect(Collectors.toList());

        groupRepository.saveAll(groupsToSave);
    }

    public List<GroupDTO> findAllGroupsForLecturerId(long lecturerId) {
        return groupRepository.findAllByLecturerId(lecturerId)
                .stream()
                .map(group -> new GroupDTO(group.getId(), group.getNumber(), group.getLecturerId()))
                .collect(Collectors.toList());
    }

    public void deleteGroupById(long id) {
        groupRepository.findById(id)
                .ifPresent(group -> groupRepository.deleteById(id));
    }

    //TODO after students are added
    public void registerToGroup() {

    }

    //TODO after students are added
    public void unregisterFromGroup() {

    }
}
