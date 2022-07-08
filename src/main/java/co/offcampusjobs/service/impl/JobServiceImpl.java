package co.offcampusjobs.service.impl;

import co.offcampusjobs.dto.JobDto;
import co.offcampusjobs.model.Job;
import co.offcampusjobs.model.Location;
import co.offcampusjobs.model.Qualification;
import co.offcampusjobs.repository.JobRepository;
import co.offcampusjobs.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    /**
     * This method returns trending jobs based on drive flag, fetches data from the database
     * @param driveFlag
     * @return
     */
    @Override
    public List<JobDto> getTrendingJob(int driveFlag) {
        List<Job> jobs = jobRepository.getByDriveFlag(driveFlag);
        List<JobDto> jobDtos = new ArrayList<>();
        for(Job job : jobs) {
            jobDtos.add(convertJobEntityToDto(job, driveFlag));
        }

        return null;
    }

    /**
     * This method convert Job class to JobDto class
     * @param job
     * @param driveFlag
     * @return
     */
    private JobDto convertJobEntityToDto(Job job, int driveFlag) {
        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());
        String locations = extractLocations(job.getLocations());
        jobDto.setName(job.getCompanyName() + " - " + job.getProfileName() + " - " + locations);
        jobDto.setCompanyName(job.getCompanyName());
        jobDto.setProfileName(job.getProfileName());
        jobDto.setLocations(locations);
        jobDto.setQualifications(extractQualifications(job.getQualifications()));
        jobDto.setCreatedAt(job.getCreatedAt());
        jobDto.setLastDateToApply(job.getLastDate());
        jobDto.setExperience(job.getMinExperience() + " - " + job.getMaxExperience());
        jobDto.setSalary(job.getMinSalary() + " - " + job.getMaxSalary());
        jobDto.setJd(job.getJd());
        jobDto.setDriveFlag(driveFlag);
        jobDto.setImageUrl(job.getImageUrl());
        jobDto.setApplyLink(jobDto.getApplyLink());
        return jobDto;
    }

    /**
     * This method extract all the qualifications from the list of qualifications
     * @param qualifications
     * @return
     */
    private String extractQualifications(List<Qualification> qualifications) {
        String qualification = "";
        for(Qualification degree : qualifications){
            if(qualification == "" ) {
                qualification = degree.getQualificationName();
            } else {
                qualification = qualification + "/" + degree.getQualificationName();
            }
        }
        return qualification;
    }

    /**
     * This method extract all the locations from the list of locations
     * @param locations
     * @return
     */
    private String extractLocations(List<Location> locations) {
        String location = "";
        for(Location city : locations) {
            if(location == "") {
                location = city.getLocationName();
            } else {
                location = location + "/" + city.getLocationName();
            }
        }
        return location;
    }
}
