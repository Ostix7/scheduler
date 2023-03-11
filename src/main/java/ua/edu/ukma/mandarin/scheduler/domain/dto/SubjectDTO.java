package ua.edu.ukma.mandarin.scheduler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SubjectDTO {
    private Long id;
    private String name;
    private int authorId;
    //Subject should be added with groups usually
    private List<GroupDTO> groupDTOList;
//    private List<StudentDTO> students; TODO: not sure whether we need this, but probably for normative disciplines
}
