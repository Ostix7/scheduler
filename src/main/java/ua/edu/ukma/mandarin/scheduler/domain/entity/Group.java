package ua.edu.ukma.mandarin.scheduler.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "group")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte number;

    @ManyToOne
    @JoinColumn
    private Teacher lecturer;

    @ManyToOne
    @JoinColumn
    private Subject subject;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "group_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Student> students;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Schedule> schedules;

}
