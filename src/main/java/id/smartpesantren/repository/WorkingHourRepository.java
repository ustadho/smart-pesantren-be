package id.smartpesantren.repository;

import id.smartpesantren.dto.WorkingHourDTO;
import id.smartpesantren.entity.WorkingHour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WorkingHourRepository extends JpaRepository<WorkingHour, String> {
    @Query("select new id.smartpesantren.dto.WorkingHourDTO(a) \n" +
            "from WorkingHour a\n"+
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    Page<WorkingHourDTO> filter(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.WorkingHourDTO(a) \n" +
            "from WorkingHour a\n"+
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q \n" +
            "order by a.checkInTime, a.name")
    Iterable<WorkingHourDTO> findAll(@Param("q") String q);

    @Query("from WorkingHour a\n"+
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and a.code = :code")
    Optional<WorkingHour> findByCode(@Param("code") String code);
}
