package co.offcampusjobs.controller;

import co.offcampusjobs.business.JobBusiness;
import co.offcampusjobs.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/job/creator")
public class UserController {

    @Autowired
    private JobBusiness jobBusiness;

    @PostMapping("/add")
    public ResponseEntity<Job> saveJob(@Valid @RequestBody Job job, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result);
            return null;
        }
        return new ResponseEntity<>(jobBusiness.saveNewJob(job), HttpStatus.CREATED);
    }
}
