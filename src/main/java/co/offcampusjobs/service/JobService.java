package co.offcampusjobs.service;

import co.offcampusjobs.dto.JobDto;

import java.util.List;

public interface JobService {
    List<JobDto> getTrendingJob(int driveFlag);
}
