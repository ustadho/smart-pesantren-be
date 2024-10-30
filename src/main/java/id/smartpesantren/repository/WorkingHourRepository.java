package id.smartpesantren.repository;

import id.smartpesantren.entity.WorkingHour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingHourRepository extends JpaRepository<WorkingHour, String> {
}
