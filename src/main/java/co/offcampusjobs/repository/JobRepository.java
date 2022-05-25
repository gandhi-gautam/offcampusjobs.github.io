package co.offcampusjobs.repository;

import co.offcampusjobs.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findAllByOrderByCreatedAtDesc(Pageable pageable);

    /**
     * This method returns all the jobs filter by an individual course name
     *
     * @param courseName
     * @param pageable
     * @return
     */
    @Query(value = "select j.id, j.apply_link, j.company_name, j.created_at, j.drive_flag, j.min_experience, " +
            "j.max_experience, j.image_url, j.profile_name, j.salary from job j inner join job_qualification jq on " +
            "j.id = jq.job_id inner join qualification q on jq.qualification_id = q.id where q.qualification_name = " +
            ":courseName",
            nativeQuery = true)
    Page<Job> getJobsByQualificationName(@Param("courseName") String courseName, Pageable pageable);

    /**
     * This controller method returns all job in particular location
     * @param city
     * @param pageable
     * @return
     */
    @Query(value = "select j.id, j.apply_link, j.company_name, j.created_at, j.drive_flag, j.min_experience," +
            "j.max_experience , j.image_url, j.profile_name, j.salary from job j inner join job_location jl on j.id = " +
            "jl.job_id inner join location l on jl.location_id = l.id where l.location_name = :city",
            nativeQuery = true)
    Page<Job> getJobsByLocation(String city, Pageable pageable);

    Page<Job> findByDriveFlag(int internshipFlag, Pageable pageable);
}
