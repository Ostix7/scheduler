package ua.edu.ukma.mandarin.scheduler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SubjectDTO {
    private Long id;
    private String name;
    private Long authorId;
    //Subject should be added with groups usually
    private List<GroupDTO> groupDTOList;

    //If subject is mandatory for some students it should be filled
    // private List<Student> students
}
