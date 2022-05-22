package co.offcampusjobs.business;

import co.offcampusjobs.dto.JobDto;
import co.offcampusjobs.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobBusiness {
    public Job saveNewJob(Job job);

    Page<JobDto> getOffCampusJobs(Pageable pageable);

    JobDto getJob(long id);
}
