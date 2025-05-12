package id.smartpesantren.repository;

import id.smartpesantren.dto.KBMAssesmentStudentQuery;
import id.smartpesantren.entity.KBMAssesment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KBMAssesmentRepository extends JpaRepository<KBMAssesment, String> {
    @Query(value = "select acrs.student_id \"studentId\", as2.name \"studentName\", as2.nis \"studentNis\", ka.id, coalesce(ka.nilai_tugas, 0) \"nilaiTugas\", \n" +
            "coalesce(ka.nilai_uts,0) \"nilaiUTS\", coalesce(ka.nilai_uas,0) \"nilaiUAS\", coalesce(ka.nilai_akhir,0) \"nilaiAkhir\"\n" +
            "from ac_class_room_student acrs \n" +
            "left join ac_student as2 on as2.id = acrs.student_id \n" +
            "left join kbm_assesment ka on ka.student_id = acrs.student_id and ka.semester=:semester and ka.subject_id = :subjectId\n" +
            "where acrs.class_room_id = :classRoomId \n" +
            "order by as2.name \n", nativeQuery = true)
    public List<KBMAssesmentStudentQuery> findKBMAssesmentStudentQueryByClassRoomId(@Param("classRoomId") String classRoomId, @Param("semester") Integer semester, @Param("subjectId") String subjectId);
}
