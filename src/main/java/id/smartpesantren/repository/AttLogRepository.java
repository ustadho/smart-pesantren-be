package id.smartpesantren.repository;

import id.smartpesantren.entity.AttLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface AttLogRepository extends JpaRepository<AttLog, String> {
    @Query("from AttLog a where a.scanDate between :startDate and :endDate")
    Page<AttLog> findAllByDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);
}
