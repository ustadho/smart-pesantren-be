package id.smartpesantren.repository;

import id.smartpesantren.entity.StudentPresence;
import id.smartpesantren.web.rest.vm.StudentPresenceVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface StudentPresenceRepository extends JpaRepository<StudentPresence, String> {
    @Query("select new id.smartpesantren.web.rest.vm.StudentPresenceVM(a) " +
            "from StudentPresence a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and (:studentId is null or a.student.id=:studentId) \n" +
            "and (:statusId is null or a.presenceStatus.id=:statusId) \n" +
            "and (cast(:startDate as date) is null or a.date >= :startDate) \n" +
            "and (cast(:endDate as date) is null or a.date <= :endDate ) \n")
    Page<StudentPresenceVM> filter(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                   @Param("statusId") Integer statusId, @Param("studentId") String studentId,
                                   Pageable p);
}
