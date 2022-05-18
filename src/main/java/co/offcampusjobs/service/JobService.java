package co.offcampusjobs.service;

import co.offcampusjobs.dto.JobDto;

import java.util.List;

public interface JobService {
    JobDto saveJob(JobDto jobDto);

    List<JobDto> getOffCampusJobs();

    JobDto getJobDto(long id);
}
