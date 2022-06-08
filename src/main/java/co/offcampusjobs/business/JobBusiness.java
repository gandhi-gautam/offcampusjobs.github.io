package co.offcampusjobs.business;

import co.offcampusjobs.model.Job;

import java.util.List;


public interface JobBusiness {
    public Job saveNewJob(Job job);

    List<Job> getOffCampusJobs();

    Job getJob(long id);

    List<Job> getJobsByQualificationName(String courseName);

    List<Job> getJobsByLocation(String city);

    List<Job> getAllJobsByDriveFlag(int driveFlag);
}
