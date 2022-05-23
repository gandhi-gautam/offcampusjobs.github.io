package co.offcampusjobs.repository;

import co.offcampusjobs.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query(value = "select j.id, j.apply_link, j.company_name, j.created_at, j.drive_type, j.experience, j.image_url, " +
            "j.location, j.profile_name, j.salary from job j inner join job_qualification jq on j.id = jq.job_id" +
            " inner join qualification q on jq.qualification_id = q.id where q.qualification_name = :courseName",
            nativeQuery = true)
    Page<Job> getJobsByQualificationName(@Param("courseName") String courseName, Pageable pageable);
}
