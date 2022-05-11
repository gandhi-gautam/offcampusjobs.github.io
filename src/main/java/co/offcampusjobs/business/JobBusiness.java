package co.offcampusjobs.business;

import co.offcampusjobs.dto.JobDto;

public interface JobBusiness {
    public JobDto saveNewJob(JobDto jobDto);
}
