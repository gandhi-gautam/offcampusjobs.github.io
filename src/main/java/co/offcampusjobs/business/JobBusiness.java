package co.offcampusjobs.business;

import co.offcampusjobs.dto.JobDto;

import java.util.List;

public interface JobBusiness {
    public JobDto saveNewJob(JobDto jobDto);

    List<JobDto> getOffCampusJobs();
}
