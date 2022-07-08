package co.offcampusjobs.repository;

import co.offcampusjobs.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    /**
     * This method returns all the job list based on drive flag
     * @param driveFlag
     * @return
     */
    List<Job> getByDriveFlag(int driveFlag);
}
