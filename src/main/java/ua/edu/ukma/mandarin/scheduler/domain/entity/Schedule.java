package ua.edu.ukma.mandarin.scheduler.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.edu.ukma.mandarin.scheduler.domain.util.Day;

@Entity
@Table(name = "schedule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Schedule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "group_id")
  private Group group;

  private Integer week;

  @Enumerated(EnumType.STRING)
  @Column(name = "week_day")
  private Day day;

  @Column(name = "start_time")
  private String startTime;

  @Column(name = "end_time")
  private String endTime;
}
