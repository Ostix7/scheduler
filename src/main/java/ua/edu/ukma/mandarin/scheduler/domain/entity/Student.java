package ua.edu.ukma.mandarin.scheduler.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;


@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    private String faculty;

    private String speciality;

    @Min(value = 1, message = "Year must not be less than 1")
    @Max(value = 6, message = "Year must not be greater than 6")
    @Column(name = "student_year")
    private Integer studentYear;

}
