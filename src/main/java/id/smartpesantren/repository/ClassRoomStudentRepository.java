package id.smartpesantren.repository;

import id.smartpesantren.dto.ClassRoomStudentDTO;
import id.smartpesantren.entity.AcademicYear;
import id.smartpesantren.entity.ClassRoomStudent;
import id.smartpesantren.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassRoomStudentRepository extends JpaRepository<ClassRoomStudent, String> {
    @Query("select new id.smartpesantren.dto.ClassRoomStudentDTO(crs) \n" +
            "from ClassRoomStudent crs \n" +
            "where crs.classRoom.id=:classRoomId \n " +
            "order by crs.student.name")
    public List<ClassRoomStudentDTO> findByClassRoomId(@Param("classRoomId") String id);

    @Query(value = "select crs.* \n" +
            "from ac_class_room_student crs " +
            "join ac_class_room cr on cr.id=crs.class_room_id\n" +
            "where crs.student_id=:studentId \n " +
            "and cr.academic_year_id=:academicYearId limit 1", nativeQuery = true)
    public ClassRoomStudent findByStudentAndAcademicYear(@Param("studentId") String studentId, @Param("academicYearId") String academicYearId);

}
