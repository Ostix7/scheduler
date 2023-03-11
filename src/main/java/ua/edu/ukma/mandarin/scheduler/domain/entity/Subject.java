package ua.edu.ukma.mandarin.scheduler.domain.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "subject")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private Teacher author;

  @OneToMany private List<Group> groups;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "subject_student",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "subject_id"))
  private List<Student> students;
}
