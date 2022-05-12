package co.offcampusjobs.controller;

import co.offcampusjobs.business.JobBusiness;
import co.offcampusjobs.dto.JobDto;
import co.offcampusjobs.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public JobDto saveJob(@ModelAttribute JobDto jobDto){
        return jobBusiness.saveNewJob(jobDto);
    }
}
