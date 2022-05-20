package co.offcampusjobs.business;

import co.offcampusjobs.dto.JobDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobBusiness {
    public JobDto saveNewJob(JobDto jobDto);

    Page<JobDto> getOffCampusJobs(Pageable pageable);

    JobDto getJob(long id);
}
