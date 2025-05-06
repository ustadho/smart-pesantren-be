package id.smartpesantren.repository;

import id.smartpesantren.dto.HalaqohDTO;
import id.smartpesantren.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HalaqohRepository extends JpaRepository<Halaqoh, String> {
    @Query(value = "select a.name \n" +
            "from halaqoh_student s " +
            "join halaqoh a on a.id=s.halaqoh_id\n" +
            "where s.student_id=:studentId \n " +
            "and a.academic_year_id=:academicYearId limit 1", nativeQuery = true)
    public String findByStudentAndAcademicYear(@Param("studentId") String studentId, @Param("academicYearId") String academicYearId);

    public Halaqoh findTop1ByPesantrenAndAcademicYear(Pesantren asrama, AcademicYear academicYear);

    @Query("select new id.smartpesantren.dto.HalaqohDTO(a) \n " +
            "from Halaqoh a " +
            "where (coalesce(:academicYear,'')='' OR a.academicYear.id=:academicYear)\n" +
            "AND (coalesce(:pesantren,'')='' OR a.pesantren.id=:pesantren)")
    Page<HalaqohDTO> filter(@Param("academicYear") String academicYear,
                            @Param("pesantren") String pesantren,
                            Pageable pageable);

    @Query("select a from Halaqoh a \n" +
            "left join fetch a.musyrifs m \n" +
            "where a.id=:id")
    Optional<Halaqoh> findByHalaqohId(@Param("id") String id);
}
