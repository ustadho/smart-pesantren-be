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
                    "\tselect pd.id, pd.student_id, pd.presence_status_id, ps.\"name\" presence_status_name,  pd.note, p.presence_date, pd.attachment  \n" +
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

    @Query(value = "with p as (\n" +
            "\tselect apks.presence_id, apks.id presence_student_id, apks.student_id, apks.presence_status_id, coalesce(ps.name,'') presence_status_name, apks.note, apks.attachment, apks.created_date\n" +
            "\tfrom ac_presence_kbm apk \n" +
            "\tjoin ac_presence_kbm_student apks on apks.presence_id=apk.id\n" +
            "\tleft join presence_status ps on ps.id=apks.presence_status_id\n" +
            "\twhere apk.schedule_id=?1\n" +
            "\tand cast(apk.presence_date as date)= cast(?2 as date)\n" +
            "), s  as (\n" +
            "\tselect count(case when p.presence_status_id=2 then p.presence_student_id else null end) izin_count, \n" +
            "\tcount(case when p.presence_status_id=3 then p.presence_student_id else null end) sakit_count,\n" +
            "\tcount(case when p.presence_status_id=0 then p.presence_student_id else null end) aplha_count\n" +
            "\tfrom p\n" +
            "), sm as (\n" +
            "\tselect count(student_id) student_count from ac_subject_schedule_student where subject_schedule_teacher_id=?1\n" +
            ")\n" +
            "\n" +
            "SELECT acrs.student_id \"studentId\", as2.name \"studentName\", as2.nis, as2.nisn, as2.photo, \n" +
            "p.presence_student_id \"presenceId\", coalesce(p.presence_status_id, 1) \"presenceStatusId\", coalesce(p.presence_status_name, 'Hadir') \"presenceStatusName\", \n" +
            "coalesce(p.note,'') note, p.attachment, p.created_date \"presenceDate\",\n" +
            "coalesce(s.izin_count,0) \"izinCount\", coalesce(s.sakit_count,0) \"sakitCount\", coalesce(s.aplha_count,0) \"aplhaCount\"\n" +
            "FROM ac_subject_schedule_teacher st \n" +
            "join ac_subject_schedule ass on ass.id=st.schedule_id \n" +
            "JOIN ac_class_room_student acrs on acrs.class_room_id = ass.class_room_id \n" +
            "join ac_student as2 on as2.id=acrs.student_id \n" +
            "left join p on p.student_id=acrs.student_id \n" +
            "left join s on true\n" +
            "left join sm on true\n" +
            "where st.id=?1\n" +
            "and coalesce(sm.student_count,0) = 0\n" +
            "\n" +
            "union all \n" +
            "\n" +
            "SELECT sss.student_id \"studentId\", as2.name \"studentName\", as2.nis, as2.nisn, as2.photo, \n" +
            "p.presence_student_id \"presenceId\", coalesce(p.presence_status_id, 1) \"presenceStatusId\", coalesce(p.presence_status_name, 'Hadir') \"presenceStatusName\", \n" +
            "coalesce(p.note,'') note, p.attachment, p.created_date \"presenceDate\",\n" +
            "coalesce(s.izin_count,0) \"izinCount\", coalesce(s.sakit_count,0) \"sakitCount\", coalesce(s.aplha_count,0) \"aplhaCount\"\n" +
            "FROM ac_subject_schedule_teacher st \n" +
            "join ac_subject_schedule ass on ass.id=st.schedule_id \n" +
            "JOIN ac_subject_schedule_student sss on sss.subject_schedule_teacher_id = st.id\n" +
            "join ac_student as2 on as2.id=sss.student_id \n" +
            "left join p on p.student_id=sss.student_id \n" +
            "left join s on true\n" +
            "left join sm on true\n" +
            "where st.id=?1\n" +
            "and coalesce(sm.student_count,0) > 0\n" +
            "order by \"studentName\" ", nativeQuery = true)
    public List<PresenceSubjectStudentDTO> findByDetailStudentByScheduleIdAndDate(String teacherScheduleId, String date);


}
