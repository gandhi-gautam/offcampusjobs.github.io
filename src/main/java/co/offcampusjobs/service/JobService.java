package co.offcampusjobs.service;
import co.offcampusjobs.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobService {
    Job saveJob(Job job);

    Page<Job> getOffCampusJobs(Pageable pageable);

    Job getJob(long id);
}
