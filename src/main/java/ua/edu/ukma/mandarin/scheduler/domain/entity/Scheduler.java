package ua.edu.ukma.mandarin.scheduler.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.edu.ukma.mandarin.scheduler.domain.util.Faculty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Scheduler {
    private int id;
    private String name;
    private Faculty faculty;

    private short year;
}
