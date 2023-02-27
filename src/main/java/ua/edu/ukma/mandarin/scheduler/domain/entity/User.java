package ua.edu.ukma.mandarin.scheduler.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @OneToOne(mappedBy = "user")
    private Teacher teacher;

    @OneToOne(mappedBy = "user")
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @Column(unique=true)
    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    @Size(min = 10, max = 70,
            message = "Password must be between 10 and 30 characters")
    private String password;

    @NotNull
    @Size(min = 2, max = 32,
            message = "First name must be between 4 and 32 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 32,
            message = "Last name must be between 4 and 32 characters")
    @Column(name = "last_name")
    private String lastName;

}
