package co.offcampusjobs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long jobId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "drive_type")
    private String driveType;

    @Column(name = "profile_name")
    private String profileName;

    @Lob
    private String qualification;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "image_url")
    private String imageUrl;

    private String location;

    private String salary;

    private String experience;

    @Column(name = "apply_link", length = 1000)
    private String applyLink;

    @ManyToMany
    @JoinTable(name = "job_qualification",
                joinColumns = @JoinColumn(name = "job_id"),
                inverseJoinColumns = @JoinColumn(name = "qualification_id"))
    private List<Qualification> qualifications = new ArrayList<>();
}
