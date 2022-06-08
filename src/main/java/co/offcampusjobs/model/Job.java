package co.offcampusjobs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Job implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long jobId;

    @NotEmpty(message = "Company Name cannot not be empty")
    @Column(name = "company_name")
    private String companyName;

    @Transient
    @NotEmpty(message = "Drive Type cannot not be empty")
    private String driveType;

    @NotEmpty(message = "Profile Name cannot not be empty")
    @Column(name = "profile_name")
    private String profileName;

    @Lob
    @Transient
    @NotEmpty(message = "Qualification cannot not be empty")
    private String qualification;


    @Column(name = "created_at")
    private LocalDate createdAt;

    @NotEmpty(message = "Image Url cannot not be empty")
    @Column(name = "image_url")
    private String imageUrl;

    @Transient
    @NotEmpty(message = "Location cannot not be empty")
    private String location;

    @NotEmpty(message = "Salary cannot not be empty")
    private String salary;

    @NotEmpty(message = "Min Experience cannot not be empty")
    @Size(min = 0, message = "Min Experience cannot be less than 0")
    @Column(name = "min_experience")
    private String minExperience;

    @NotEmpty(message = "Max Experience cannot not be empty")
    @Size(min = 0, message = "Max Experience cannot be less than 0")
    @Column(name = "max_experience")
    private String maxExperience;

    @NotEmpty(message = "Apply Link cannot not be empty")
    @Column(name = "apply_link", length = 1000)
    private String applyLink;

    @Column(name = "drive_flag", length = 2)
    private int driveFlag;

    @Lob
    private String jd;

    @Lob
    private String skills;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "job_qualification",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_id"))
    private List<Qualification> qualifications = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "job_location",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    private List<Location> locations = new ArrayList<>();
}
