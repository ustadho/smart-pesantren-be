package id.smartpesantren.repository;

import id.smartpesantren.entity.PresensiKegiatan;
import id.smartpesantren.web.rest.vm.PresensiKegiatanVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;

public interface PresensiKegiatanRepository extends JpaRepository<PresensiKegiatan, String> {
    @Query("select new id.smartpesantren.web.rest.vm.PresensiKegiatanVM(a) " +
            "from PresensiKegiatan a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and (:studentId is null or a.santri.id=:studentId) \n" +
            "and (:jenisKegiatan is null or a.jenisKegiatan.id=:jenisKegiatan) \n" +
            "and (cast(:startDate as date) is null or cast(a.tanggal as date) >= :startDate) \n" +
            "and (cast(:endDate as date) is null or cast(a.tanggal as date) <= :endDate ) \n")
    Page<PresensiKegiatanVM> filter(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                    @Param("jenisKegiatan") Integer jenisKegiatanId, @Param("studentId") String studentId,
                                    Pageable p);
}
