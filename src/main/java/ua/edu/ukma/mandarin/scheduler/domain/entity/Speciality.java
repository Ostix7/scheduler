package ua.edu.ukma.mandarin.scheduler.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "speciality")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Speciality {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "faculty_id")
  private Faculty faculty;
}
