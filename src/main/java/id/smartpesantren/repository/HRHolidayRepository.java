package id.smartpesantren.repository;

import id.smartpesantren.entity.HRHoliday;
import id.smartpesantren.web.rest.vm.HRHolidayVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HRHolidayRepository extends JpaRepository<HRHoliday, String> {
    @Query("select new  id.smartpesantren.web.rest.vm.HRHolidayVM(hh) \n" +
            "from HRHoliday hh \n" +
            "where hh.foundation.id=?#{principal.foundationId} \n" +
            "and (coalesce(:y, 0)=0 OR YEAR(hh.eventDate)=:y) \n" +
            "and upper(coalesce(hh.description,'')) like :q ")
    public Page<HRHolidayVM> filter(@Param("y") Integer year, @Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.web.rest.vm.HRHolidayVM(hh) \n" +
            "from HRHoliday hh \n" +
            "where hh.foundation.id=?#{principal.foundationId} \n" +
            "and (coalesce(:y, 0)=0 OR YEAR(hh.eventDate)=:y) \n" +
            "and upper(hh.description) like :q ")
    public List<HRHolidayVM> filter(@Param("y") Integer year, @Param("q") String q);

    @Query("from HRHoliday h \n" +
            "where h.foundation.id=?#{principal.foundationId} \n" +
            "and h.eventDate=:eventDate")
    public Optional<HRHoliday> findByEventDate(@Param("eventDate")LocalDate eventDate);
}
