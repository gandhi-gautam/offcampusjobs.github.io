package co.offcampusjobs.business;

import co.offcampusjobs.dto.JobDto;

import java.util.List;

public interface JobBusiness {
    List<JobDto> getTrendingJob(String driveType);
}
