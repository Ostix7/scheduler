package ua.edu.ukma.mandarin.scheduler.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Integer teacherId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @NotEmpty
    private String faculty;

    @NotNull
    @NotEmpty
    private String cathedra;

    @NotEmpty
    private String rank;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Group> groupsToTeach;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Subject> subjectsAuthor;

}