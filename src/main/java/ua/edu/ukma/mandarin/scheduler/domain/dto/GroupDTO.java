package ua.edu.ukma.mandarin.scheduler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GroupDTO {
    private Long id;
    private byte number;
    private long lecturerId;
}
