package id.smartpesantren.repository;

import id.smartpesantren.dto.AsramaMappingStudentDTO;
import id.smartpesantren.dto.SantriListDTO;
import id.smartpesantren.entity.AsramaMapping;
import id.smartpesantren.entity.AsramaMappingStudent;
import id.smartpesantren.entity.ClassRoomStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsramaMappingStudentRepository extends JpaRepository<AsramaMappingStudent, String> {
    @Query("select new id.smartpesantren.dto.AsramaMappingStudentDTO(ams) \n" +
            "from AsramaMappingStudent ams \n" +
            "where ams.asramaMapping.id=:id \n " +
            "order by ams.student.name")
    public List<AsramaMappingStudentDTO> findByMappingId(@Param("id") String id);

    @Query(value = "select crs.* \n" +
            "from asrama_mapping_student ams " +
            "join asrama_mapping am on am.id=ams.asrama_mapping_id\n" +
            "where ams.student_id=:studentId \n " +
            "and am.academic_year_id=:academicYearId limit 1", nativeQuery = true)
    public ClassRoomStudent findByStudentAndAcademicYear(@Param("studentId") String studentId, @Param("academicYearId") String academicYearId);

}
