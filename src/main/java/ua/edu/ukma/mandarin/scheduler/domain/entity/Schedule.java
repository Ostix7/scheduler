package ua.edu.ukma.mandarin.scheduler.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.edu.ukma.mandarin.scheduler.domain.util.Day;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //We do not need subject, as it is connected to group
    @ManyToOne
    @JoinColumn
    private Group group;

    private Day day;
    private Timestamp startTime;
    private Timestamp endTime;


}
