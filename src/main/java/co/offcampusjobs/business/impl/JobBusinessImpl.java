package co.offcampusjobs.business.impl;

import co.offcampusjobs.business.JobBusiness;
import co.offcampusjobs.model.Job;
import co.offcampusjobs.model.Qualification;
import co.offcampusjobs.service.JobService;
import co.offcampusjobs.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Component
public class JobBusinessImpl implements JobBusiness {
    @Autowired
    private JobService jobService;

    @Autowired
    private QualificationService qualificationService;

    public JobBusinessImpl() {
    }

    /**
     * Scope : [This method saves the job in the DataBase]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 11-05-2022]
     */
    @Override
    public Job saveNewJob(Job job) {
        job.setImageUrl(changeImageURL(job.getImageUrl()));
        job = extractQualifications(job, job.getQualification());
        job.setCreatedAt(LocalDate.now());
        job = jobService.saveJob(job);
        return job;
    }

    /**
     * This method extract all the qualifications from string csv format and convert them into individual qualification
     * object that have many to many functionality implemented
     * @param job
     * @param qualificationString
     * @return
     */
    private Job extractQualifications(Job job, String qualificationString) {
        Map<String, Qualification> qualificationMap = qualificationService.getAllQualifications();
        String[] qualifications = qualificationString.split(",");
        for(String courseName : qualifications){
            Qualification qualification = null;
            if(qualificationMap.containsKey(courseName.trim())){
                qualification = qualificationMap.get(courseName.trim());
            } else {
                qualification = new Qualification(courseName.trim());
            }
            job.getQualifications().add(qualification);
            qualification.getJobs().add(job);
        }
        return job;
    }

    /**
     * Scope : [This method returns all the off-campus-jobs from database]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 15-05-2022]
     */
    @Override
    public Page<Job> getOffCampusJobs(Pageable pageable) {
        return jobService.getOffCampusJobs(pageable);
    }

    /**
     * Scope : [This method changes the Old url to new formated url]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 11-05-2022]
     */
    private String changeImageURL(String url){
        return "https://drive.google.com/uc?export=view&id=" + url.split("/")[5];
    }

    /**
     * Scope : [This method returns a job for a particular Id]
     * Author : [Sarthak Singh]
     * Comment : [refactoring date: 17-05-2022]
     */
    @Override
    public Job getJob(long id){
        return jobService.getJob(id);
    }

    @Override
    public Page<Job> getJobsByQualificationName(String courseName, Pageable pageable) {
        return jobService.getJobsByQualificationName(courseName, pageable);
    }
}
