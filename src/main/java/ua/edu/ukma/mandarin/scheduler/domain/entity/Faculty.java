package ua.edu.ukma.mandarin.scheduler.domain.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "faculty")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Faculty {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "faculty")
  private List<Cathedra> cathedras;

  @OneToMany(mappedBy = "faculty")
  private List<Speciality> specialities;
}
