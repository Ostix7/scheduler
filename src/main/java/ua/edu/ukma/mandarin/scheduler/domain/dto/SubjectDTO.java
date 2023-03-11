package ua.edu.ukma.mandarin.scheduler.domain.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SubjectDTO {
  private Long id;
  private String name;
  private Long authorId;
  // Subject should be added with groups usually
  private List<GroupDTO> groupDTOList;
  // TODO: not sure whether we need this, but probably for normative disciplines
  // private List<StudentDTO> students;
}
