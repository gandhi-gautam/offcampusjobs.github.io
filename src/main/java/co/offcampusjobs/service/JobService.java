package co.offcampusjobs.service;

import co.offcampusjobs.dto.JobDto;
import co.offcampusjobs.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobService {
    Job saveJob(Job job);

    Page<Job> getOffCampusJobs(Pageable pageable);

    JobDto getJobDto(long id);
}
