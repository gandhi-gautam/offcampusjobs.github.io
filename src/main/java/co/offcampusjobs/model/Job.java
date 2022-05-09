package co.offcampusjobs.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
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

    public Job() {
    }

    public Job(long jobId, String companyName, String driveType, String profileName, String qualification,
               LocalDate createdAt, String imageUrl, String location, String salary, String experience, String applyLink) {
        this.jobId = jobId;
        this.companyName = companyName;
        this.driveType = driveType;
        this.profileName = profileName;
        this.qualification = qualification;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
        this.location = location;
        this.salary = salary;
        this.experience = experience;
        this.applyLink = applyLink;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getApplyLink() {
        return applyLink;
    }

    public void setApplyLink(String applyLink) {
        this.applyLink = applyLink;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", companyName='" + companyName + '\'' +
                ", driveType='" + driveType + '\'' +
                ", profileName='" + profileName + '\'' +
                ", qualification='" + qualification + '\'' +
                ", createdAt=" + createdAt +
                ", imageUrl='" + imageUrl + '\'' +
                ", location='" + location + '\'' +
                ", salary='" + salary + '\'' +
                ", experience='" + experience + '\'' +
                ", applyLink='" + applyLink + '\'' +
                '}';
    }
}
