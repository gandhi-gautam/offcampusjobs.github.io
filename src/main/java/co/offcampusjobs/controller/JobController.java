package co.offcampusjobs.controller;

import co.offcampusjobs.business.JobBusiness;
import co.offcampusjobs.business.LocationBusiness;
import co.offcampusjobs.business.QualificationBusiness;
import co.offcampusjobs.model.Job;
import co.offcampusjobs.util.JobConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
     * Scope : [This method saves Job when url is '/job' with post mapping]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 26-05-2022]
     */
    @PostMapping("/job")
    public ResponseEntity<Job> saveJob(@Valid @ModelAttribute Job job, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result);
            return null;
        }
        return new ResponseEntity<>(jobBusiness.saveNewJob(job), HttpStatus.CREATED);
    }

    /**
     * Scope : [This method returns all the off-campus-jobs from database, It will trigger when url is
     * /{drive type}/{page} per page it shows 15 job item then page number changes.]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 26-05-2022]
     */
    @GetMapping("/trendingjobs/{drive}")
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

    @GetMapping("/qualification/{courseName}")
    public ResponseEntity<List<Job>> getByQualificationName(@PathVariable(JobConstant.COURSE_NAME) String courseName) {
        List<Job> jobs = jobBusiness.getJobsByQualificationName(courseName);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/location/{city}")
    public ResponseEntity<List<Job>> getByLocation(@PathVariable(JobConstant.CITY) String city) {

        List<Job> jobs = jobBusiness.getJobsByLocation(city);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    /**
     * Scope : [This method returns a job for a particular Id]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 26-05-2022]
     */
    @GetMapping("/{drive}/{id}")
    public ResponseEntity<Job> getJob(@PathVariable(JobConstant.DRIVE) String drive, @PathVariable(JobConstant.ID) long id) {
        Job job = jobBusiness.getJob(id);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }
}
