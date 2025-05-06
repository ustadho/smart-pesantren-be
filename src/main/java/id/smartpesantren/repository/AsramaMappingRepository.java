package id.smartpesantren.repository;

import id.smartpesantren.dto.AsramaMappingDTO;
import id.smartpesantren.dto.AsramaMappingStudentDTO;
import id.smartpesantren.dto.ClassRoomStudentDTO;
import id.smartpesantren.dto.SantriListDTO;
import id.smartpesantren.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AsramaMappingRepository extends JpaRepository<AsramaMapping, String> {
    @Query(value = "select a.name \n" +
            "from asrama_mapping_student ams " +
            "join asrama_mapping am on am.id=ams.asrama_mapping_id\n" +
            "join asrama a on a.id=am.asrama_id\n" +
            "where ams.student_id=:studentId \n " +
            "and am.academic_year_id=:academicYearId limit 1", nativeQuery = true)
    public String findByStudentAndAcademicYear(@Param("studentId") String studentId, @Param("academicYearId") String academicYearId);

    public AsramaMapping findTop1ByAsramaAndAcademicYear(Asrama asrama, AcademicYear academicYear);

    @Query("select new id.smartpesantren.dto.AsramaMappingDTO(a) \n " +
            "from AsramaMapping a " +
            "where (coalesce(:academicYear,'')='' OR a.academicYear.id=:academicYear)\n" +
            "AND (coalesce(:pesantren,'')='' OR a.asrama.pesantren.id=:pesantren)\n" +
            "")
    Page<AsramaMappingDTO> filter(@Param("academicYear") String academicYear,
                                  @Param("pesantren") String pesantren,
                                  Pageable pageable);

    @Query("select a from AsramaMapping a \n" +
            "left join fetch a.musyrifs m \n" +
            "where a.id=:id")
    Optional<AsramaMapping> findByMappingId(@Param("id") String id);
}
