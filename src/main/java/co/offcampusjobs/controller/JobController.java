package co.offcampusjobs.controller;

import co.offcampusjobs.business.JobBusiness;
import co.offcampusjobs.dto.JobDto;
import co.offcampusjobs.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute("jobDTO", new JobDto());
        return "creator/SaveJob";
    }

    /**
     * Scope : [This method saves Job when url is '/job' with post mapping]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 12-05-2022]
     */
    @ResponseBody
    @PostMapping("/job")
    public JobDto saveJob(@Valid @ModelAttribute JobDto jobDto, BindingResult result){
        if(result.hasErrors()){
            System.out.println(result);
            return null;
        }
        return jobBusiness.saveNewJob(jobDto);
    }

    /**
     * Scope : [This method returns all the off-campus-jobs from database, It will trigger when url is
     * /{drive type}/{page} per page it shows 15 job item then page number changes.]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 20-05-2022]
     */
    @GetMapping("/{drive}/{page}")
    public String viewJobListPage(@PathVariable("drive") String drive, @PathVariable("page") Integer page, Model model){
        Pageable pageable = PageRequest.of(page, Constant.PAGE_SIZE);
        if(drive.equals("off-campus-jobs")){
            model.addAttribute("title", "OCJ - off-campus-jobs");
            model.addAttribute("drive", "off-campus-jobs");
            model.addAttribute("year", LocalDate.now().getYear());
            model.addAttribute("jobs", jobBusiness.getOffCampusJobs(pageable));
            return "viewer/ViewJobList";
        }
        return null;
    }

    /**
     * Scope : [This method returns a job for a particular Id]
     * Author : [Sarthak Singh]
     * Comment : [refactoring date: 17-05-2022]
     */
    @GetMapping("/{drive}/{id}")
    public String getJob(@PathVariable("drive") String drive, @PathVariable("id") long id, Model model){
        JobDto jobDto = jobBusiness.getJob(id);
        model.addAttribute("title", "Off Campus Jobs - "+jobDto.getCompanyName() + " " + jobDto.getProfileName());
        model.addAttribute("job", jobDto);
        return "viewer/ViewJob";
    }
}
