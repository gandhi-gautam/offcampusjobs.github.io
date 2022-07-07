package co.offcampusjobs.repository;

import co.offcampusjobs.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationRepository extends JpaRepository<Qualification, Long> {
}
