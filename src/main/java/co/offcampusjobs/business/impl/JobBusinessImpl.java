package co.offcampusjobs.business.impl;

import co.offcampusjobs.business.JobBusiness;
import co.offcampusjobs.dto.JobDto;
import co.offcampusjobs.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobBusinessImpl implements JobBusiness {

    @Autowired
    private JobService jobService;

    /**
     * This method return list of jobs based on trending type, It also converts to drive type based on drive flag
     * @param driveType
     * @return
     */
    @Override
    public List<JobDto> getTrendingJob(String driveType) {

        List<JobDto> jobDtos = jobService.getTrendingJob(getDriveFlag(driveType));
        for(JobDto jobDto : jobDtos) {
            jobDto.setDriveType(getDriveType(jobDto.getDriveFlag()));
            jobDto.setDriveFlag(-1);
        }
        return jobDtos;
    }

    /**
     * This method converts drive type to drive flag
     * @param driveType
     * @return
     */
    private int getDriveFlag(String driveType) {
        if(driveType.equals("internship")){
            return 0;
        } else if(driveType.equals("fresher")) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * This method converts drive flag to drive type
     * @param driveFlag
     * @return
     */
    private String getDriveType(int driveFlag) {
        if(driveFlag == 0 ){
            return "internship";
        } else if(driveFlag == 1) {
            return "fresher";
        } else {
            return "experience";
        }
    }
}
