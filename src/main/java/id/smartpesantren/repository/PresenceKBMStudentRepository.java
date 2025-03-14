package id.smartpesantren.repository;

import id.smartpesantren.dto.PresenceSubjectStudentDTO;
import id.smartpesantren.entity.PresenceKBMStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PresenceKBMStudentRepository extends JpaRepository<PresenceKBMStudent, String> {

    @Query(value =
            "with student_map as (\n" +
                    "\tselect count(student_id) from ac_subject_schedule_student where subject_schedule_teacher_id=:id\n" +
                    "), \n" +
                    "a as (\n" +
                    "\tselect p.id, pd.student_id, pd.presence_status_id, ps.\"name\" presence_status_name,  pd.note, p.presence_date, pd.attachment  \n" +
                    "\tfrom ac_presence_kbm p \n" +
                    "\tjoin ac_presence_kbm_student pd on pd.presence_id=p.id \n" +
                    "\tleft join presence_status ps on pd.presence_status_id = ps.id \n" +
                    "\twhere p.schedule_id = :id\n" +
                    "\tand p.presence_date=current_date  \n" +
                    ")\n" +
                    "select * from (\n" +
                    "\tselect acrs.student_id \"studentId\", s.nis, s.nisn, s.name \"studentName\", a.id \"presenceId\", coalesce(a.presence_status_id, 1) \"presenceStatusId\", \n" +
                    "\tcoalesce(presence_status_name, 'HADIR') \"presenceStatusName\", a.note, a.presence_date \"presenceDate\", a.attachment \n" +
                    "\tfrom ac_class_room acr \n" +
                    "\tjoin ac_class_room_student acrs on acr.id = acrs.class_room_id  \n" +
                    "\tjoin ac_student s on s.id=acrs.student_id \n" +
                    "\tjoin ac_subject_schedule ss on ss.class_room_id = acr.id \n" +
                    "\tjoin ac_subject_schedule_teacher asst on asst.schedule_id = ss.id \n" +
                    "\tleft join a on a.student_id=acrs.student_id\n" +
                    "\twhere asst.id = :id and (select coalesce(m.count,0) from student_map m ) = 0 \n" +
                    "\t\n" +
                    "\tunion all\n" +
                    "\t\n" +
                    "\tselect ass.student_id \"studentId\", s.nis, s.nisn, s.name \"studentName\", a.id \"presenceId\", coalesce(a.presence_status_id, 1) \"presenceStatusId\", \n" +
                    "\tcoalesce(presence_status_name, 'HADIR') \"presenceStatusName\", a.note, a.presence_date \"presenceDate\", a.attachment \n" +
                    "\tfrom ac_subject_schedule_teacher asst \n" +
                    "\tjoin ac_subject_schedule_student ass on ass.subject_schedule_teacher_id = asst.id \n" +
                    "\tjoin ac_student s on s.id=ass.student_id \n" +
                    "\tleft join a on a.student_id=ass.student_id\n" +
                    "\twhere asst.id = :id and (select coalesce(m.count,0) from student_map m ) > 0 \n" +
                    ") s\n" +
                    "order by \"studentName\"", nativeQuery = true)
    public List<PresenceSubjectStudentDTO> findDetailStudentsBySubjectTeacherId(@Param("id") String id);
}
