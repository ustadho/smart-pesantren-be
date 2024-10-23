package id.smartpesantren.repository;

import id.smartpesantren.entity.EducationLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EducationLevelRepository extends JpaRepository<EducationLevel, Integer> {
    @Query("from EducationLevel e " +
            "order by e.id")
    Iterable<EducationLevel> findAllLevel();
}
