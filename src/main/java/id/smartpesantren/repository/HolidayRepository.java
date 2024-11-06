package id.smartpesantren.repository;

import id.smartpesantren.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Integer> {
}
