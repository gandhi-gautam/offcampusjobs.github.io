package co.offcampusjobs.model;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;

    @NotEmpty(message = "Email Cannot be empty")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotEmpty(message = "first Name cannot not be empty")
    private String firstName;

    @NotEmpty(message = "last Name cannot not be empty")
    private String lastName;

    private String role;

    private Date lastLoginLate;

    private Date lastLoginDateDisplay;

    private Date joinDate;

    private boolean isActive;

    private boolean isNotLocked;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Job> jobs = new ArrayList<>();
}
