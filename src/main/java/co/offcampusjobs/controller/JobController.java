package co.offcampusjobs.controller;

import co.offcampusjobs.business.JobBusiness;
import co.offcampusjobs.model.Job;
import co.offcampusjobs.util.CommonConstant;
import co.offcampusjobs.util.JobConstant;
import co.offcampusjobs.util.UserConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class JobController {
    @Autowired
    private JobBusiness jobBusiness;

    /**
     * Scope : [This method returns a Save Job Form when url is '/job' with get mapping]
     * Author : [Sarthak Singh]
     * Comment : [refactoring date: 12-05-2022]
     */
    @GetMapping("/job")
    public String saveJobForm(Model model) {
        model.addAttribute(JobConstant.JOB, new Job());
        return UserConstant.CREATOR + "/SaveJob";
    }

    /**
     * Scope : [This method saves Job when url is '/job' with post mapping]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 12-05-2022]
     */
    @ResponseBody
    @PostMapping("/job")
    public Job saveJob(@Valid @ModelAttribute Job job, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result);
            return null;
        }
        return jobBusiness.saveNewJob(job);
    }

    /**
     * Scope : [This method returns all the off-campus-jobs from database, It will trigger when url is
     * /{drive type}/{page} per page it shows 15 job item then page number changes.]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 20-05-2022]
     */
    @GetMapping("job/{drive}/{page}")
    public String getTrendingJobs(@PathVariable(JobConstant.DRIVE) String drive, @PathVariable(CommonConstant.Page)
    Integer page, Model model) {
        Pageable pageable = PageRequest.of(page, CommonConstant.PAGE_SIZE);
        Page<Job> jobs = null;
        if (drive.equals(JobConstant.OFFCAMPUSJOBS)) {
            jobs = jobBusiness.getOffCampusJobs(pageable);
            model.addAttribute(CommonConstant.TITLE, "OCJ - " + JobConstant.OFFCAMPUSJOBS);
            model.addAttribute(CommonConstant.DRIVE, JobConstant.OFFCAMPUSJOBS);
            model.addAttribute(CommonConstant.YEAR, LocalDate.now().getYear());
            model.addAttribute(JobConstant.JOBS, jobs);
            model.addAttribute(CommonConstant.TOATAL_PAGES, jobs.getTotalPages());
        }
        model.addAttribute(CommonConstant.CURRENT_PAGE, page);

        return UserConstant.VIEWER + "/ViewJobList";
    }

    /**
     * This controller method is called when we select any qualification, it returns the list of all the jobs for that
     * individual qualification
     *
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/job/qualification/{courseName}/{page}")
    public String getByQualificationName(@PathVariable(JobConstant.COURSE_NAME) String courseName,
                                         @PathVariable(CommonConstant.Page) Integer page, Model model) {
        Pageable pageable = PageRequest.of(page, CommonConstant.PAGE_SIZE);
        Page<Job> jobs = jobBusiness.getJobsByQualificationName(courseName, pageable);
        model.addAttribute(CommonConstant.TITLE, "OCJ - " + JobConstant.QUALIFICATION);
        model.addAttribute(CommonConstant.DRIVE, JobConstant.QUALIFICATION);
        model.addAttribute(CommonConstant.YEAR, LocalDate.now().getYear());
        model.addAttribute(JobConstant.JOBS, jobs);
        model.addAttribute(CommonConstant.TOATAL_PAGES, jobs.getTotalPages());
        model.addAttribute(CommonConstant.CURRENT_PAGE, page);
        return UserConstant.VIEWER + "/ViewJobList";
    }

    /**
     * This controller method returns all job in particular location
     * @param city
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/job/location/{city}/{page}")
    public String getByLocation(@PathVariable(JobConstant.CITY) String city,
                                         @PathVariable(CommonConstant.Page) Integer page, Model model) {
        Pageable pageable = PageRequest.of(page, CommonConstant.PAGE_SIZE);
        Page<Job> jobs = jobBusiness.getJobsByLocation(city, pageable);

        model.addAttribute(CommonConstant.TITLE, "OCJ - " + JobConstant.LOCATION);
        model.addAttribute(CommonConstant.DRIVE, JobConstant.LOCATION);
        model.addAttribute(CommonConstant.YEAR, LocalDate.now().getYear());
        model.addAttribute(JobConstant.JOBS, jobs);
        model.addAttribute(CommonConstant.TOATAL_PAGES, jobs.getTotalPages());
        model.addAttribute(CommonConstant.CURRENT_PAGE, page);
        return UserConstant.VIEWER + "/ViewJobList";
    }

    /**
     * Scope : [This method returns a job for a particular Id]
     * Author : [Sarthak Singh]
     * Comment : [refactoring date: 17-05-2022]
     */
    @GetMapping("/{drive}/{id}")
    public String getJob(@PathVariable(JobConstant.DRIVE) String drive, @PathVariable(JobConstant.ID) long id, Model model) {
        Job job = jobBusiness.getJob(id);
        model.addAttribute(CommonConstant.TITLE, CommonConstant.OFFCAMPUSJOBS + " - " + job.getCompanyName() +
                " " + job.getProfileName());
        model.addAttribute(JobConstant.JOB, job);
        return UserConstant.VIEWER + "/ViewJob";
    }
}
