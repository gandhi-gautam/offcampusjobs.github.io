package co.offcampusjobs.service.impl;

import co.offcampusjobs.dto.JobDto;
import co.offcampusjobs.dto.QualificationDto;
import co.offcampusjobs.model.Job;
import co.offcampusjobs.model.Qualification;
import co.offcampusjobs.repository.JobRepository;
import co.offcampusjobs.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public JobDto saveJob(JobDto jobDto) {
        List<QualificationDto> qualificationDtos = jobDto.getQualificationDtos();
        List<Qualification> qualifications = new ArrayList<>();
        for(QualificationDto qualificationDto : qualificationDtos){
            qualifications.add(new Qualification(qualificationDto));
        }
        Job job = convertToJobEntity(jobDto);
        job.setQualifications(qualifications);
        job = jobRepository.save(job);
        return convertTOJobDto(job);
    }

    /**
     * Scope : [This method returns all the off-campus-jobs from database]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 15-05-2022]
     */
    @Override
    public Page<JobDto> getOffCampusJobs(Pageable pageable) {
        Page<Job> jobPage = jobRepository.findAllByOrderByCreatedAtDesc(pageable);
        return toPageObjectDto(jobPage);
    }

    /**
     * This method takes Pages of jobs and return pages of job dto
     * @param jobs
     * @return
     */
    private Page<JobDto> toPageObjectDto(Page<Job> jobs) {
        Page<JobDto> dtos  = jobs.map(this::convertTOJobDto);
        return dtos;
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
        if(jobDto.getCompanyName() == null){
            jobDto.setCompanyName(job.getCompanyName());
            jobDto.setDriveType(job.getDriveType());
            jobDto.setProfileName(job.getProfileName());
            jobDto.setImageUrl(job.getImageUrl());
            jobDto.setLocation(job.getLocation());
            jobDto.setSalary(job.getSalary());
            jobDto.setExperience(job.getExperience());
            jobDto.setApplyLink(job.getApplyLink());
        }
        List<Qualification> qualifications = job.getQualifications();
        List<QualificationDto> qualificationDtos = convertToQualificationDto(jobDto, qualifications);
        jobDto.setQualificationDtos(qualificationDtos);
        return jobDto;
    }

    /**
     * This method convert the list of qualification and return List of qualificationDto
     * @param jobDto
     * @param qualifications
     * @return
     */
    private List<QualificationDto> convertToQualificationDto(JobDto jobDto, List<Qualification> qualifications){
        List<QualificationDto> qualificationDtos = new ArrayList<>();
        for(Qualification qualification : qualifications){
            QualificationDto qualificationDto = new QualificationDto(qualification);
            qualificationDto.getJobDtos().add(jobDto);
            qualificationDtos.add(qualificationDto);
        }
        return qualificationDtos;
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
        job.setCreatedAt(LocalDate.now());
        job.setImageUrl(jobDto.getImageUrl());
        job.setLocation(jobDto.getLocation());
        job.setSalary(jobDto.getSalary());
        job.setExperience(jobDto.getExperience());
        job.setApplyLink(jobDto.getApplyLink());
        return job;
    }

    /**
     * Scope : [This method returns a job for a particular Id]
     * Author : [Sarthak Singh]
     * Comment : [refactoring date: 17-05-2022]
     */
    @Override
    public JobDto getJobDto(long id){
        return convertTOJobDto(jobRepository.getById(id));
    }


}
