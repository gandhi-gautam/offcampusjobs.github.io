package co.offcampusjobs.repository;

import co.offcampusjobs.model.Insights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsightsRepository extends JpaRepository<Insights, Long> {
}
