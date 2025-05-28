package id.smartpesantren.repository;

import id.smartpesantren.entity.Student;
import id.smartpesantren.entity.TahfidzSetoran;
import id.smartpesantren.service.dto.TahfidzSetoranVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface TahfidzSetoranRepository extends JpaRepository<TahfidzSetoran, String> {
    Optional<TahfidzSetoran> findByStudentAndTanggalAndWaktu(Student student, Date tanggal, String waktu);

    @Query("select new id.smartpesantren.service.dto.TahfidzSetoranVM(" +
            "a.id, a.student.id, a.student.name, a.student.nis, a.tanggal, a.waktu, a.targetMulai, a.targetSampai, " +
            "a.qiraah, a.hifdz, a.tikrar, a.setorUlangDari, a.setorUlangSampai, a.murajaah, a.nilai) " +
            "from TahfidzSetoran a " +
            "where (cast(:startDate as date) is null or cast(a.tanggal as date) >= :startDate) \n" +
            "and (cast(:endDate as date) is null or cast(a.tanggal as date) <= :endDate) \n" +
            "and (:studentId is null or a.student.id=:studentId)")
    Page<TahfidzSetoranVM> filter(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("studentId") String studentId, Pageable p);
}
