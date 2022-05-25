package co.offcampusjobs.service;

import co.offcampusjobs.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobService {
    Job saveJob(Job job);

    Page<Job> getOffCampusJobs(Pageable pageable);

    Job getJob(long id);

    Page<Job> getJobsByQualificationName(String courseName, Pageable pageable);

    Page<Job> getJobsByLocation(String city, Pageable pageable);

    Page<Job> getAllJobsByDriveFlag(int driveFlag, Pageable pageable);
}
