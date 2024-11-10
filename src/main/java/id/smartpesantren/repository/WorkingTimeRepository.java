package id.smartpesantren.repository;

import id.smartpesantren.dto.WorkingTimeDTO;
import id.smartpesantren.entity.WorkingTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WorkingTimeRepository extends JpaRepository<WorkingTime, String> {
    @Query("select new id.smartpesantren.dto.WorkingTimeDTO(a) \n" +
            "from WorkingTime a\n"+
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    Page<WorkingTimeDTO> filter(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.WorkingTimeDTO(a) \n" +
            "from WorkingTime a\n"+
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q \n" +
            "order by a.checkInTime, a.name")
    Iterable<WorkingTimeDTO> findAll(@Param("q") String q);

    @Query("from WorkingTime a\n"+
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and a.code = :code")
    Optional<WorkingTime> findByCode(@Param("code") String code);
}
