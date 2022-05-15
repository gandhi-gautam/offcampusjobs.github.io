package co.offcampusjobs.service.impl;

import co.offcampusjobs.dto.JobDto;
import co.offcampusjobs.model.Job;
import co.offcampusjobs.repository.JobRepository;
import co.offcampusjobs.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
    public JobDto saveJob(JobDto jobDto) {
        Job job = jobRepository.save(convertToJobEntity(jobDto));
        return convertTOJobDto(job);
    }

    /**
     * Scope : [This method Converts Job Entity to JobDto object]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 11-05-2022]
     */
    private JobDto convertTOJobDto(Job job) {
        JobDto jobDto = new JobDto();
        jobDto.setJobId(job.getJobId());
        jobDto.setCreatedAt(job.getCreatedAt());
        return jobDto;
    }

    /**
     * Scope : [This method Converts JobDto to Job Entity object]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 11-05-2022]
     */
    private Job convertToJobEntity(JobDto jobDto) {
        Job job = new Job();
        job.setCompanyName(jobDto.getCompanyName());
        job.setDriveType(jobDto.getDriveType());
        job.setProfileName(jobDto.getProfileName());
        job.setQualification(jobDto.getQualification());
        job.setCreatedAt(LocalDate.now());
        job.setImageUrl(jobDto.getImageUrl());
        job.setLocation(jobDto.getLocation());
        job.setSalary(jobDto.getSalary());
        job.setExperience(jobDto.getExperience());
        job.setApplyLink(jobDto.getApplyLink());
        return job;
    }
}
