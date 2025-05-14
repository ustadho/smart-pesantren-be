package id.smartpesantren.repository;

import id.smartpesantren.dto.PrestasiDTO;
import id.smartpesantren.entity.Prestasi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PrestasiRepository extends JpaRepository<Prestasi, String> {
    @Query("select new id.smartpesantren.dto.PrestasiDTO(" +
            "a.id, a.jenisPrestasi.id, a.jenisPrestasi.name, a.student.id, a.student.name, a.student.nis, a.student.nisn, a.student.classRoom.name, a.tanggal, a.keterangan, a.attachment) " +
            "from Prestasi a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and (:studentId is null or a.student.id=:studentId) \n" +
            "and (:jenisPrestasi is null or a.jenisPrestasi.id=:jenisPrestasi) \n" +
            "and (cast(:startDate as date) is null or cast(a.tanggal as date) >= :startDate) \n" +
            "and (cast(:endDate as date) is null or cast(a.tanggal as date) <= :endDate ) \n")

    Page<PrestasiDTO> filter(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("jenisPrestasi") String jenisPrestasi, @Param("studentId") String studentId, Pageable pageable);
    void deleteById(String id);
}
