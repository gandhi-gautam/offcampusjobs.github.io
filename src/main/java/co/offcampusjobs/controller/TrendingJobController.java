package co.offcampusjobs.controller;

import co.offcampusjobs.business.JobBusiness;
import co.offcampusjobs.dto.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/job")
public class TrendingJobController {

    @Autowired
    private JobBusiness jobBusiness;

    /**
     * This api is called when user is searching for trending jobs, it returns all the job based on there type
     * @param driveType
     * @return
     */
    @GetMapping("/trendingjobs/{driveType}")
    public ResponseEntity<List<JobDto>> trendingJobs(@PathVariable("driveType") String driveType){
        List<JobDto> jobDtos = null;
        if(driveType.trim().toLowerCase().equals("internship")){
            jobDtos = jobBusiness.getTrendingJob("internship");
        } else if(driveType.trim().toLowerCase().equals("fresher")) {
            jobDtos = jobBusiness.getTrendingJob("fresher");
        } else if(driveType.trim().toLowerCase().equals("experience")) {
            jobDtos = jobBusiness.getTrendingJob("experience");
        }
        return new ResponseEntity<>(jobDtos, HttpStatus.OK);
    }
}
