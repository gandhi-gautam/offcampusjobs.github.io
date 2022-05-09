package co.offcampusjobs.dto;

public class JobDto {
    private long jobId;
    private String companyName;
    private String driveType;
    private String profileName;
    private String qualification;
    private String imageUrl;
    private String location;
    private String salary;
    private String experience;
    private String applyLink;

    public JobDto() {
    }

    public JobDto(long jobId, String companyName, String driveType, String profileName, String qualification,
                  String imageUrl, String location, String salary, String experience, String applyLink) {
        this.jobId = jobId;
        this.companyName = companyName;
        this.driveType = driveType;
        this.profileName = profileName;
        this.qualification = qualification;
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
        return "JobDto{" +
                "jobId=" + jobId +
                ", companyName='" + companyName + '\'' +
                ", driveType='" + driveType + '\'' +
                ", profileName='" + profileName + '\'' +
                ", qualification='" + qualification + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", location='" + location + '\'' +
                ", salary='" + salary + '\'' +
                ", experience='" + experience + '\'' +
                ", applyLink='" + applyLink + '\'' +
                '}';
    }
}
