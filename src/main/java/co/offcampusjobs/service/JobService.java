package co.offcampusjobs.service;

import co.offcampusjobs.dto.JobDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobService {
    JobDto saveJob(JobDto jobDto);

    Page<JobDto> getOffCampusJobs(Pageable pageable);

    JobDto getJobDto(long id);
}
