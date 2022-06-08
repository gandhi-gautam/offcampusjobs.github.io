package co.offcampusjobs.repository;

import co.offcampusjobs.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findAllByOrderByCreatedAtDesc();

    /**
     * This method returns all the jobs filter by an individual course name
     *
     * @param courseName
     * @return
     */
    @Query(value = "select j.id, j.apply_link, j.company_name, j.created_at, j.drive_flag, j.min_experience, " + "j.max_experience, j.image_url, j.profile_name, j.salary from job j inner join job_qualification jq on " + "j.id = jq.job_id inner join qualification q on jq.qualification_id = q.id where q.qualification_name = " + ":courseName", nativeQuery = true)
    List<Job> getJobsByQualificationName(@Param("courseName") String courseName);

    /**
     * This controller method returns all job in particular location
     *
     * @param city
     * @return
     */
    @Query(value = "select j.id, j.apply_link, j.company_name, j.created_at, j.drive_flag, j.min_experience," + "j.max_experience , j.image_url, j.profile_name, j.salary from job j inner join job_location jl on j.id = " + "jl.job_id inner join location l on jl.location_id = l.id where l.location_name = :city", nativeQuery = true)
    List<Job> getJobsByLocation(String city);

    List<Job> findByDriveFlag(int internshipFlag);
}
