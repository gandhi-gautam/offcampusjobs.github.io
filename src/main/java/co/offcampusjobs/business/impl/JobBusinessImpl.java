package co.offcampusjobs.business.impl;

import co.offcampusjobs.business.JobBusiness;
import co.offcampusjobs.dto.JobDto;
import co.offcampusjobs.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        return jobService.saveJob(jobDto);
    }

    /**
     * Scope : [This method changes the Old url to new formated url]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 11-05-2022]
     */
    private String changeImageURL(String url){
        return "https://drive.google.com/uc?export=view&id=" + url.split("/")[5];
    }
}
