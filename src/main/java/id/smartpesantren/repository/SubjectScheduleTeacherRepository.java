package id.smartpesantren.repository;

import id.smartpesantren.dto.ScheduleTeacherListQuery;
import id.smartpesantren.entity.SubjectScheduleTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SubjectScheduleTeacherRepository extends JpaRepository<SubjectScheduleTeacher, String> {
    @Query("from SubjectScheduleTeacher a " +
            "left join fetch a.students " +
            "where a.id=:id")
    public Optional<SubjectScheduleTeacher> findOneById(@Param("id") String id);

    @Query(value = "with w as (\n" +
            "\tWITH w AS (\n" +
            "\t\tselect current_date - ((extract(dow from current_date)::::int + 7 - 0) % 7) AS awal_minggu  -- Ahad\n" +
            "\t)\n" +
            "\tSELECT\n" +
            "\t  generate_series(\n" +
            "\t    awal_minggu,\n" +
            "\t    awal_minggu + 6,\n" +
            "\t    interval '1 day'\n" +
            "\t  )::::date AS tanggal\n" +
            "\tFROM w\n" +
            ")\n" +
            "select  ass.id, w.tanggal \"scheduleDate\",ass.day_id \"dayId\", as2.\"name\" \"subjectName\", ata.seq::::text || case when ata.seq=atb.seq then '' else '-'||atb.seq::::text end \"timeSeq\",\n" +
            "ata.start_time \"startTime\", atb.end_time \"endTime\", coalesce(i.name) \"institutionName\" , cr.\"name\" \"classRoom\", case when cr.sex='F' then 'Putri' else 'Putra' end sex, \n" +
            "apk.created_date \"presenceDate\"\n" +
            "from ac_subject_schedule_teacher st \n" +
            "join ac_subject_schedule ass on ass.id=st.schedule_id \n" +
            "join ac_activity_time ata on ata.id = ass.activity_time_start_id \n" +
            "join ac_activity_time atb on atb.id = ass.activity_time_end_id\n" +
            "join ac_subject as2 on as2.id=st.subject_id \n" +
            "join ac_class_room cr on cr.id=ass.class_room_id \n" +
            "join institution i on i.id=cr.institution_id \n" +
            "join w on EXTRACT(DOW FROM w.tanggal)=ass.day_id and (:all IS true OR w.tanggal=cast(:scheduleDate as date))\n" +
            "left join ac_presence_kbm apk on apk.schedule_id = st.schedule_id and apk.presence_date=w.tanggal \n" +
            "where st.teacher_id = :teacherId\n" +
            "order by ass.day_id, ata.seq", nativeQuery = true)
    public List<ScheduleTeacherListQuery> findScheduleTeacherList(@Param("teacherId") String teacherId,
                                                                  @Param("all") boolean all,
                                                                  @Param("scheduleDate") String scheduleDate);
}
