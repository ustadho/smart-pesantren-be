package id.smartpesantren.repository;

import id.smartpesantren.dto.PelanggaranDTO;
import id.smartpesantren.entity.Pelanggaran;
import id.smartpesantren.entity.PresensiKegiatan;
import id.smartpesantren.web.rest.vm.PresensiKegiatanVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface PelanggaranRepository extends JpaRepository<Pelanggaran, String> {
    @Query("select new id.smartpesantren.dto.PelanggaranDTO(" +
            "a.id, a.jenisPelanggaran.id, a.jenisPelanggaran.name, a.student.id, a.student.name, a.student.nis, a.student.nisn, a.student.classRoom.name, a.tanggal, a.keterangan, a.attachment) " +
            "from Pelanggaran a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and (:studentId is null or a.student.id=:studentId) \n" +
            "and (:jenisPelanggaran is null or a.jenisPelanggaran.id=:jenisPelanggaran) \n" +
            "and (cast(:startDate as date) is null or cast(a.tanggal as date) >= :startDate) \n" +
            "and (cast(:endDate as date) is null or cast(a.tanggal as date) <= :endDate ) \n")
    Page<PelanggaranDTO> filter(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                @Param("jenisPelanggaran") Integer jenisPelanggaran, @Param("studentId") String studentId,
                                Pageable p);
}
