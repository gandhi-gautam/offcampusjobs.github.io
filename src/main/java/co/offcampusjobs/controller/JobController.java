package co.offcampusjobs.controller;

import co.offcampusjobs.business.JobBusiness;
import co.offcampusjobs.business.LocationBusiness;
import co.offcampusjobs.business.QualificationBusiness;
import co.offcampusjobs.model.Job;
import co.offcampusjobs.util.constant.JobConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {
    @Autowired
    private JobBusiness jobBusiness;

    @Autowired
    private QualificationBusiness qualificationBusiness;

    @Autowired
    private LocationBusiness locationBusiness;

    /**
     * Scope : [This method returns all the trending jobs based on drive type
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 3-06-2022]
     */
    @GetMapping("/find/trendingjobs/{drive}")
    public ResponseEntity<List<Job>> getTrendingJobs(@PathVariable(JobConstant.DRIVE) String drive) {
        List<Job> jobs = null;
        if (drive.trim().toLowerCase().equals(JobConstant.OFFCAMPUSJOBS.toLowerCase(Locale.ROOT))) {
            jobs = jobBusiness.getOffCampusJobs();

        } else if (drive.trim().toLowerCase().equals(JobConstant.INTERNSHIP.toLowerCase())) {
            jobs = jobBusiness.getAllJobsByDriveFlag(JobConstant.INTERNSHIP_FLAG);

        } else if (drive.trim().toLowerCase().equals(JobConstant.FRESHER.toLowerCase())) {
            jobs = jobBusiness.getAllJobsByDriveFlag(JobConstant.FRESHER_FLAG);

        } else if (drive.trim().toLowerCase().equals(JobConstant.EXPERIENCE.toLowerCase())) {
            jobs = jobBusiness.getAllJobsByDriveFlag(JobConstant.EXPERIENCE_FLAG);
        }
        assert jobs != null;
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    /*    *//**
     * This api returns all the jobs by course name
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 3-06-2022]
     *
     * @param courseName
     * @return
     *//*
    @GetMapping("/find/qualification/{courseName}")
    public ResponseEntity<List<Job>> getByQualificationName(@PathVariable(JobConstant.COURSE_NAME) String courseName) {
        List<Job> jobs = jobBusiness.getJobsByQualificationName(courseName);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    *//**
     * This api returns all the jobs by city name
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 3-06-2022]
     *
     * @param city
     * @return
     *//*
    @GetMapping("/find/location/{city}")
    public ResponseEntity<List<Job>> getByLocation(@PathVariable(JobConstant.CITY) String city) {
        List<Job> jobs = jobBusiness.getJobsByLocation(city);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }*/

    /**
     * Scope : [This method returns a job for a particular Id]
     * Author : [Gautam Gandhi]x
     * Comment : [refactoring date: 26-05-2022]
     *
     * @param id
     * @return
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<Job> getJob(@PathVariable(JobConstant.ID) long id) {
        Job job = jobBusiness.getJob(id);
        if (job == null) {
            throw new RuntimeException("Job not found of id: " + id);
        }
        return new ResponseEntity<>(job, HttpStatus.OK);
    }
}
