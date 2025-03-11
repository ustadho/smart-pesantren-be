package id.smartpesantren.repository;

import id.smartpesantren.dto.PresenceSubjectStudentDTO;
import id.smartpesantren.entity.PresenceKBMStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PresenceKBMStudentRepository extends JpaRepository<PresenceKBMStudent, String> {

    @Query(value =
            "with a as (\n" +
            "\tselect p.id, pd.student_id, pd.presence_status_id, ps.\"name\" presence_status_name,  pd.note, p.presence_date, pd.attachment  \n" +
            "\tfrom ac_presence_kbm p \n" +
            "\tjoin ac_presence_kbm_student pd on pd.presence_id=p.id \n" +
            "\tleft join presence_status ps on pd.presence_status_id = ps.id \n" +
            "\twhere p.schedule_id = :id\n" +
            "\tand p.presence_date=current_date  \n" +
            ")\n" +
            "select acrs.student_id \"studentId\", s.nis, s.nisn, s.name \"studentName\", a.id \"presenceId\", coalesce(a.presence_status_id, 1) \"presenceStatusId\", \n" +
            "coalesce(presence_status_name, 'HADIR') \"presenceStatusName\", a.note, a.presence_date \"presenceDate\", a.attachment \n" +
            "from ac_class_room acr \n" +
            "join ac_class_room_student acrs on acr.id = acrs.class_room_id  \n" +
            "join ac_student s on s.id=acrs.student_id \n" +
            "join ac_subject_schedule2 ss on ss.class_room_id = acr.id \n" +
            "left join a on a.student_id=acrs.student_id\n" +
            "where ss.id = :id\n" +
            "order by s.name", nativeQuery = true)
    public List<PresenceSubjectStudentDTO> findDetailStudentsByClassRoomId(@Param("id") String id);
}
