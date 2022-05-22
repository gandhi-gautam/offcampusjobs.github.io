package co.offcampusjobs.controller;

import co.offcampusjobs.business.JobBusiness;
import co.offcampusjobs.dto.JobDto;
import co.offcampusjobs.model.Job;
import co.offcampusjobs.util.Constant;
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
    public String saveJobForm(Model model){
        model.addAttribute("job", new Job());
        return "creator/SaveJob";
    }

    /**
     * Scope : [This method saves Job when url is '/job' with post mapping]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 12-05-2022]
     */
    @ResponseBody
    @PostMapping("/job")
    public Job saveJob(@Valid @ModelAttribute Job job, BindingResult result){
        if(result.hasErrors()){
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
    @GetMapping("jobs/{drive}/{page}")
    public String viewJobListPage(@PathVariable("drive") String drive, @PathVariable("page") Integer page, Model model){
        Pageable pageable = PageRequest.of(page, Constant.PAGE_SIZE);
        Page<Job> jobs = null;
        if(drive.equals("off-campus-jobs")){
            jobs = jobBusiness.getOffCampusJobs(pageable);
            model.addAttribute("title", "OCJ - off-campus-jobs");
            model.addAttribute("drive", "off-campus-jobs");
            model.addAttribute("year", LocalDate.now().getYear());
            model.addAttribute("jobs", jobBusiness.getOffCampusJobs(pageable));
            model.addAttribute("totalPages", jobs.getTotalPages());
        }
        model.addAttribute("currentPage", page);

        return "viewer/ViewJobList";
    }

    /**
     * Scope : [This method returns a job for a particular Id]
     * Author : [Sarthak Singh]
     * Comment : [refactoring date: 17-05-2022]
     */
    @GetMapping("/{drive}/{id}")
    public String getJob(@PathVariable("drive") String drive, @PathVariable("id") long id, Model model){
        Job job = jobBusiness.getJob(id);
        model.addAttribute("title", "Off Campus Jobs - "+job.getCompanyName() + " " + job.getProfileName());
        model.addAttribute("job", job);
        return "viewer/ViewJob";
    }
}
