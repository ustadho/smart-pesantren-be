package id.smartpesantren.repository;

import id.smartpesantren.dto.PresenceSubjectStudentDTO;
import id.smartpesantren.entity.PresenceKBM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PresenceKBMStudentRepository extends JpaRepository<PresenceKBM, String> {

    @Query(value = "with a as (\n" +
            "\tselect apk.id, apk.student_id, apk.presence_status_id, ps.\"name\" presence_status_name,  apk.note, apk.presence_date, apk.attachment  \n" +
            "\tfrom ac_presence_kbm apk \n" +
            "\tleft join presence_status ps on apk.presence_status_id = ps.id \n" +
            "\twhere apk.subject_schedule_id = :id\n" +
            "\tand apk.presence_date=current_date  \n" +
            ")\n" +
            "select acrs.student_id \"studentId\", s.nis, s.nisn, s.name \"studentName\", a.id \"presenceId\", coalesce(a.presence_status_id, 1) \"presenceStatusId\", \n" +
            "coalesce(presence_status_name, 'HADIR') \"presenceStatusName\", a.note, a.presence_date \"presenceDate\", a.attachment \n" +
            "from ac_class_room acr \n" +
            "join ac_class_room_student acrs on acr.id = acrs.class_room_id  \n" +
            "join ac_student s on s.id=acrs.student_id \n" +
            "left join a on a.student_id=acrs.student_id\n" +
            "where acr.id = :id\n" +
            "order by s.name\n", nativeQuery = true)
    public List<PresenceSubjectStudentDTO> findDetailStudentsByClassRoomId(@Param("id") String id);
}
