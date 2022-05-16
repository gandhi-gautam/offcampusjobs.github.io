package co.offcampusjobs.controller;

import co.offcampusjobs.business.JobBusiness;
import co.offcampusjobs.dto.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

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
     * Scope : [This method returns all the off-campus-jobs from database, It will trigger when url is /{drive type}]
     * Author : [Gautam Gandhi]
     * Comment : [refactoring date: 15-05-2022]
     */
    @GetMapping("/{drive}")
    public String viewJobListPage(@PathVariable("drive") String drive, Model model){
        if(drive.equals("off-campus-jobs")){
            model.addAttribute("title", "OCJ - off-campus-jobs");
            model.addAttribute("drive", "Off-Campus-Jobs");
            model.addAttribute("year", LocalDate.now().getYear());
            model.addAttribute("jobs", jobBusiness.getOffCampusJobs());
            return "viewer/ViewJobList";
        }
        return null;
    }

    @GetMapping("/{drive}/{id}")
    public String getJob(@PathVariable("drive") String drive, @PathVariable("id") long id, Model model){
        model.addAttribute("job", jobBusiness.getJob(id));
        return "viewer/ViewJob";
    }
}
