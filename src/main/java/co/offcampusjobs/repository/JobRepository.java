package co.offcampusjobs.repository;

import co.offcampusjobs.dto.JobDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobDto, Long> {
}
