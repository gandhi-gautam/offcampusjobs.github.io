package co.offcampusjobs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long jobId;

    @NotEmpty(message = "Company Name cannot not be empty")
    @Column(name = "company_name")
    private String companyName;

    @NotEmpty(message = "Drive Type cannot not be empty")
    @Column(name = "drive_type")
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

    @NotEmpty(message = "Location cannot not be empty")
    private String location;

    @NotEmpty(message = "Salary cannot not be empty")
    private String salary;

    @NotEmpty(message = "Experience cannot not be empty")
    private String experience;

    @NotEmpty(message = "Apply Link cannot not be empty")
    @Column(name = "apply_link", length = 1000)
    private String applyLink;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "job_qualification",
                joinColumns = @JoinColumn(name = "job_id"),
                inverseJoinColumns = @JoinColumn(name = "qualification_id"))
    private List<Qualification> qualifications = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "job_locations",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    private Set<Location> locations;
}
