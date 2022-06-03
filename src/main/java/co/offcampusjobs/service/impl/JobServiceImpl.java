package co.offcampusjobs.service.impl;

import co.offcampusjobs.model.Job;
import co.offcampusjobs.repository.JobRepository;
import co.offcampusjobs.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    /**
     * Scope : [This method saves the job in the DataBase]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 11-05-2022]
     */
    @Override
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    /**
     * Scope : [This method returns all the off-campus-jobs from database]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 15-05-2022]
     */
    @Override
    public List<Job> getOffCampusJobs() {
        return jobRepository.findAllByOrderByCreatedAtDesc();
    }

    /**
     * Scope : [This method returns a job for a particular Id]
     * Author : [Sarthak Singh]
     * Comment : [refactoring date: 17-05-2022]
     */
    @Override
    public Job getJob(long id) {
        return jobRepository.getById(id);
    }

    /**
     * This method calls the repository custom method that returns all the jobs having an individual course name as
     * basic requirements
     *
     * @param courseName
     * @return
     */
    @Override
    public List<Job> getJobsByQualificationName(String courseName) {
        return jobRepository.getJobsByQualificationName(courseName);
    }

    /**
     * This controller method returns all job in particular location
     * @param city
     * @return
     */
    @Override
    public List<Job> getJobsByLocation(String city) {
        return jobRepository.getJobsByLocation(city);
    }

    @Override
    public List<Job> getAllJobsByDriveFlag(int driveFlag) {
        return jobRepository.findByDriveFlag(driveFlag);
    }


}
