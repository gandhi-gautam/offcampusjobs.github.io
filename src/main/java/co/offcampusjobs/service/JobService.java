package co.offcampusjobs.service;

import co.offcampusjobs.model.Job;

import java.util.List;

public interface JobService {
    Job saveJob(Job job);

    List<Job> getOffCampusJobs();

    Job getJob(long id);

    List<Job> getJobsByQualificationName(String courseName);

    List<Job> getJobsByLocation(String city);

    List<Job> getAllJobsByDriveFlag(int driveFlag);
}
