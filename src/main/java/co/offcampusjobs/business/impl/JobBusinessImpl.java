package co.offcampusjobs.business.impl;

import co.offcampusjobs.business.JobBusiness;
import co.offcampusjobs.dto.JobDto;
import co.offcampusjobs.dto.QualificationDto;
import co.offcampusjobs.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobBusinessImpl implements JobBusiness {
    @Autowired
    private JobService jobService;

    /**
     * Scope : [This method saves the job in the DataBase]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 11-05-2022]
     */
    @Override
    public JobDto saveNewJob(JobDto jobDto) {
        jobDto.setImageUrl(changeImageURL(jobDto.getImageUrl()));
        String qualifications = jobDto.getQualification();
        jobDto = getQualifications(jobDto, qualifications.split(","));
        jobDto = jobService.saveJob(jobDto);
        jobDto.setQualification(qualifications);
        jobDto.setQualificationDtos(null);
        return jobDto;
    }

    /**
     * this method split all the qualification and set int the job dto object
     * @param jobDto
     * @param courses
     * @return
     */
    private JobDto getQualifications(JobDto jobDto, String[] courses){
        List<QualificationDto> qualificationDtos = new ArrayList<>();
        for(String course : courses){
            QualificationDto qualificationDto = new QualificationDto();
            jobDto.getQualificationDtos().add(qualificationDto);
            qualificationDto.setQualificationName(course.trim());
            qualificationDto.getJobDtos().add(jobDto);
            qualificationDtos.add(qualificationDto);
        }
        return jobDto;
    }

    /**
     * Scope : [This method returns all the off-campus-jobs from database]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 15-05-2022]
     */
    @Override
    public Page<JobDto> getOffCampusJobs(Pageable pageable) {
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
    public JobDto getJob(long id){
        return jobService.getJobDto(id);
    }
}
