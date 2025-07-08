package id.smartpesantren.repository;

import id.smartpesantren.dto.PresenceKBMTeacherQuery;
import id.smartpesantren.entity.PresenceKBM;
import id.smartpesantren.entity.PresenceKBMStudent;
import id.smartpesantren.entity.SubjectSchedule;
import id.smartpesantren.entity.SubjectScheduleTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface PresenceKBMRepository extends JpaRepository<PresenceKBM, String> {
    Optional<PresenceKBM> findBySubjectScheduleTeacherAndPresenceDate(SubjectScheduleTeacher st, LocalDate presenceDate);

    @Query(value = "with sm as (\n" +
            "\tselect count(student_id) student_count from ac_subject_schedule_student where subject_schedule_teacher_id=?1\n" +
            "),\n" +
            "a as (\n" +
            "\tselect sum(student_count) as student_count\n" +
            "\tfrom(\n" +
            "\t\tselect count(1) as student_count\n" +
            "\t\tfrom ac_subject_schedule_teacher st\n" +
            "\t\tjoin ac_subject_schedule ass on ass.id = st.schedule_id \n" +
            "\t\tjoin ac_class_room_student acrs on acrs.class_room_id = ass.class_room_id\n" +
            "\t\tleft join sm on true\n" +
            "\t\twhere st.id = ?1 and coalesce(sm.student_count, 0) = 0\n" +
            "\t\tunion all\n" +
            "\t\tselect count(1) as student_count\n" +
            "\t\tfrom ac_subject_schedule_teacher st\n" +
            "\t\tjoin ac_subject_schedule_student sss on sss.subject_schedule_teacher_id = st.id\n" +
            "\t\tleft join sm on true\n" +
            "\t\twhere st.id = ?1 and coalesce(sm.student_count, 0) > 0\n" +
            "\t) a\n" +
            "),\n" +
            "b as (\n" +
            "\tselect\n" +
            "\t\tcount(case when apks.presence_status_id = 0 then 1 end) as alpha,\n" +
            "\t\tcount(case when apks.presence_status_id = 2 then 1 end) as izin,\n" +
            "\t\tcount(case when apks.presence_status_id = 3 then 1 end) as sakit\n" +
            "\tfrom ac_presence_kbm_student apks  \n" +
            "\tjoin ac_presence_kbm apk on apk.id = apks.presence_id  \n" +
            "\twhere apk.schedule_id = ?1\n" +
            "\tand cast(apk.presence_date as date) = cast(?2 as date)\n" +
            ")\n" +
            "select  \n" +
            "\tass.id, \n" +
            "\tst.subject_id as \"subjectId\", \n" +
            "\tas2.name as \"subjectName\", \n" +
            "\tata.seq as \"fromSeq\", \n" +
            "\tatb.seq as \"toSeq\",\n" +
            "\tata.start_time as \"startTime\", \n" +
            "\tatb.end_time as \"endTime\",\n" +
            "\tcoalesce(i.name, '') as \"institutionName\", \n" +
            "\tass.class_room_id as \"classRoomId\", \n" +
            "\tcr.name as \"classRoomName\", \n" +
            "\tcase when cr.sex = 'F' then 'Putri' else 'Putra' end as sex, \n" +
            "\tapk.id as \"presenceId\", apk.created_date as \"presenceDate\", \n" +
            "\tapk.presence_status_id as \"presenceStatusId\", \n" +
            "\tps.name as \"presenceStatusName\", \n" +
            "\tcoalesce(a.student_count, 0) as \"studentCount\", \n" +
            "\tcoalesce(b.alpha, 0) as \"alphaCount\", \n" +
            "\tcoalesce(b.izin, 0) as \"izinCount\", \n" +
            "\tcoalesce(b.sakit, 0) as \"sakitCount\", \n" +
            "\tcoalesce(apk.pertemuan_ke, (select count(1) from ac_presence_kbm where schedule_id = st.id)+1) \"pertemuanKe\"\n" +
            "from ac_subject_schedule_teacher st \n" +
            "join ac_subject_schedule ass on ass.id = st.schedule_id \n" +
            "join ac_activity_time ata on ata.id = ass.activity_time_start_id \n" +
            "join ac_activity_time atb on atb.id = ass.activity_time_end_id\n" +
            "join ac_subject as2 on as2.id = st.subject_id \n" +
            "join ac_class_room cr on cr.id = ass.class_room_id \n" +
            "join ac_class_level cl on cl.id = cr.level_id\n" +
            "join institution i on i.id = cr.institution_id \n" +
            "left join ac_presence_kbm apk on apk.schedule_id = st.id \n" +
            "\tand cast(apk.presence_date as date) = cast(?2 as date)\n" +
            "left join presence_status ps on ps.id = apk.presence_status_id \n" +
            "left join a on true \n" +
            "left join b on true\n" +
            "where st.id = ?1\n" +
            "order by ass.day_id, ata.seq\n" +
            "\n", nativeQuery = true)
    public PresenceKBMTeacherQuery findPresenceKBMTeacherQuery(String scheduleId, String presenceDate);
}
