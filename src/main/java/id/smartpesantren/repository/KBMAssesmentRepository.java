package id.smartpesantren.repository;

import id.smartpesantren.dto.KBMAssesmentListQuery;
import id.smartpesantren.dto.KBMAssesmentStudentQuery;
import id.smartpesantren.entity.KBMAssesment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KBMAssesmentRepository extends JpaRepository<KBMAssesment, String> {
    @Query(value = "select acr.institution_id \"institutionId\", ka.subject_id \"subjectId\", acrs.student_id \"studentId\", as2.name \"studentName\", as2.nis \"studentNis\", ka.id, coalesce(ka.nilai_harian, 0) \"nilaiHarian\", \n" +
            "coalesce(ka.nilai_ketrampilan,0) \"nilaiKetrampilan\", coalesce(ka.nilai_sikap,0) \"nilaiSikap\", coalesce(ka.nilai_pts,0) \"nilaiPts\", coalesce(ka.nilai_pas,0) \"nilaiPas\", coalesce(ka.nilai_akhir,0) \"nilaiAkhir\"\n" +
            "from ac_class_room_student acrs \n" +
            "join ac_class_room acr on acr.id = acrs.class_room_id \n" +
            "left join ac_student as2 on as2.id = acrs.student_id \n" +
            "left join kbm_assesment ka on ka.student_id = acrs.student_id and ka.semester=:semester and ka.subject_id = :subjectId\n" +
            "where acrs.class_room_id = :classRoomId \n" +
            "order by as2.name \n", nativeQuery = true)
    public List<KBMAssesmentStudentQuery> findKBMAssesmentStudentQueryByClassRoomId(@Param("classRoomId") String classRoomId, @Param("semester") Integer semester, @Param("subjectId") String subjectId);

    @Query(value = "with a as (\n" +
            "\tselect distinct acl.\"level\", asst.teacher_id, asst.subject_id, as2.\"name\" subject_name, ss.class_room_id, \n" +
            "\tacr.name class_room_name, acr.institution_id, i.name institution_name\n" +
            "\tfrom ac_subject_schedule ss\n" +
            "\tjoin ac_subject_schedule_teacher asst on asst.schedule_id =ss.id \n" +
            "\tjoin ac_subject as2 on as2.id=asst.subject_id \n" +
            "\tjoin ac_class_room acr on acr.id=ss.class_room_id \n" +
            "\tjoin ac_class_level acl on acl.id=acr.level_id \n" +
            "\tjoin institution i on i.id=acr.institution_id \n" +
            "\twhere asst.teacher_id =:teacherId\n" +
            "\tand acr.academic_year_id = :academicYearId\n" +
            "\tgroup by acr.institution_id, acl.\"level\", asst.teacher_id, asst.subject_id, as2.\"name\", ss.class_room_id, acr.name, i.name\n" +
            "), sc as (\n" +
            "\tselect acrs.class_room_id, count(acrs.student_id) student_count\n" +
            "\tfrom ac_class_room_student acrs \n" +
            "\twhere acrs.class_room_id in (select class_room_id from a)\n" +
            "\tgroup by acrs.class_room_id\n" +
            "), ass as (\n" +
            "\tselect ka.class_room_id, ka.subject_id, \n" +
            "\tsum(case when ka.nilai_harian > 0 then 1 else 0 end) nilaiHarianCount,\n" +
            "\tsum(case when ka.nilai_ketrampilan > 0 then 1 else 0 end) nilaiKetrampilanCount,\n" +
            "\tsum(case when ka.nilai_sikap > 0 then 1 else 0 end) nilaiSikapCount,\n" +
            "\tsum(case when ka.nilai_pts > 0 then 1 else 0 end) nilaiPtsCount,\n" +
            "\tsum(case when ka.nilai_pas > 0 then 1 else 0 end) nilaiPasCount,\n" +
            "\tsum(case when ka.nilai_akhir > 0 then 1 else 0 end) nilaiAkhirCount\n" +
            "\tfrom kbm_assesment ka \n" +
            "\tleft join a on a.class_room_id=ka.class_room_id and a.subject_id=ka.subject_id\n" +
            "\twhere ka.semester=:semester" +
            "\tgroup by ka.class_room_id, ka.subject_id\n" +
            ")\t\n" +
            "select a.level, a.teacher_id \"teacherId\", a.subject_id \"subjectId\", a.subject_name \"subjectName\", \n" +
            "a.class_room_id \"classRoomId\", a.class_room_name \"classRoomName\", a.institution_id \"institutionId\", a.institution_name \"institutionName\", \n" +
            "coalesce(sc.student_count, 0) \"studentCount\", coalesce(ass.nilaiHarianCount,0) \"nilaiHarianCount\", \n" +
            "coalesce(ass.nilaiKetrampilanCount, 0) \"nilaiKetrampilanCount\", coalesce(ass.nilaiSikapCount, 0) \"nilaiSikapCount\", \n" +
            "coalesce(ass.nilaiPtsCount, 0) \"nilaiPtsCount\", coalesce(ass.nilaiPasCount, 0) \"nilaiPasCount\", \n" +
            "coalesce(ass.nilaiAkhirCount, 0) \"nilaiAkhirCount\"\n" +
            "from a \n" +
            "join sc on sc.class_room_id=a.class_room_id\n" +
            "left join ass on ass.class_room_id=a.class_room_id and ass.subject_id=a.subject_id\n" +
            "order by a.level ", nativeQuery = true)
    public List<KBMAssesmentListQuery> findAssesmentListByYearAndTeacherAndSemester(@Param("academicYearId") String academicYearId, @Param("teacherId") String teacherId, @Param("semester") Integer semester);
}
