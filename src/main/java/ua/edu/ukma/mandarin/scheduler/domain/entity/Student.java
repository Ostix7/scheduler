package ua.edu.ukma.mandarin.scheduler.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import ua.edu.ukma.mandarin.scheduler.domain.entity.security.Principal;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "principal_id")
  private Principal principal;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @ManyToOne
  @JoinColumn(name = "speciality_id")
  private Speciality speciality;

  @Min(value = 1, message = "Year must not be less than 1")
  @Max(value = 6, message = "Year must not be greater than 6")
  @Column(name = "study_year")
  private Integer studyYear;
}
