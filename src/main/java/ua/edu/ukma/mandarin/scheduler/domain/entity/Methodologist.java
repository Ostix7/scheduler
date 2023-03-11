package ua.edu.ukma.mandarin.scheduler.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import ua.edu.ukma.mandarin.scheduler.domain.entity.security.Principal;

@Entity
@Table(name = "methodologist")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Methodologist {

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
}
