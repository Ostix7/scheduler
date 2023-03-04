package ua.edu.ukma.mandarin.scheduler.domain.entity;

import jakarta.persistence.*;
import lombok.*;

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

    private Long lecturerId; //TODO: add one to many

    @ManyToOne
    @JoinColumn
    private Subject subject;
}
