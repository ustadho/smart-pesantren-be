package id.smartpesantren.repository;

import id.smartpesantren.entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DayRepository extends JpaRepository<Day, Integer> {
    @Query("from Day d " +
            "order by case when d.id=0 then 10 else d.id end")
    List<Day> findAllDays();
}
