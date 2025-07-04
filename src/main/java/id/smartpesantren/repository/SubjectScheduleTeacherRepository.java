package id.smartpesantren.repository;

import id.smartpesantren.dto.ScheduleTeacherListQuery;
import id.smartpesantren.dto.ScheduleTeacherSubjectListQuery;
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
            "and cr.academic_year_id in (select id from academic_year where is_default=true and foundation_id=?#{principal.foundationId})\n" +
            "order by ass.day_id, ata.seq", nativeQuery = true)
    public List<ScheduleTeacherListQuery> findScheduleTeacherList(@Param("teacherId") String teacherId,
                                                                  @Param("all") boolean all,
                                                                  @Param("scheduleDate") String scheduleDate);

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
            "join w on EXTRACT(DOW FROM w.tanggal)=ass.day_id \n" +
            "left join ac_presence_kbm apk on apk.schedule_id = st.schedule_id and apk.presence_date=w.tanggal \n" +
            "where st.teacher_id = :teacherId\n" +
            "and st.subject_id = :subjectId\n" +
            "and ass.class_room_id = :classRoomId\n" +
            "and cr.academic_year_id in (select id from academic_year where is_default=true and foundation_id=?#{principal.foundationId})\n" +
            "order by ass.day_id, ata.seq", nativeQuery = true)
    public List<ScheduleTeacherListQuery> findScheduleTeacherListBySubjectIdAndClassRoom(
                                        @Param("teacherId") String teacherId,
                                        @Param("subjectId") String subjectId,
                                        @Param("classRoomId") String classRoomId
                                        );


    @Query(value = "with a as (\n" +
            "\tselect  ass.id, cl.level, ass.day_id, \n" +
            "\tcase ass.day_id when 0 then 'Ahad' when 1 then 'Senin' when 2 then 'Selasa' when 3 then 'Rabu' when 4 then 'Kamis' when 5 then 'Jumat' when 6 then 'Sabtu' end \"dayName\", \n" +
            "\tst.subject_id \"subjectId\", as2.\"name\" \"subjectName\", atb.seq - ata.seq  + 1 jumlah_jam, coalesce(i.name) \"institutionName\" , \n" +
            "\tass.class_room_id \"classRoomId\", cr.\"name\" \"classRoomName\", case when cr.sex='F' then 'Putri' else 'Putra' end sex\n" +
            "\tfrom ac_subject_schedule_teacher st \n" +
            "\tjoin ac_subject_schedule ass on ass.id=st.schedule_id \n" +
            "\tjoin ac_activity_time ata on ata.id = ass.activity_time_start_id \n" +
            "\tjoin ac_activity_time atb on atb.id = ass.activity_time_end_id\n" +
            "\tjoin ac_subject as2 on as2.id=st.subject_id \n" +
            "\tjoin ac_class_room cr on cr.id=ass.class_room_id \n" +
            "\tjoin ac_class_level cl on cl.id=cr.level_id\n" +
            "\tjoin institution i on i.id=cr.institution_id \n" +
            "\twhere st.teacher_id = :teacherId\n" +
            "\tAND cr.academic_year_id in (select id from academic_year where is_default=true and foundation_id=?#{principal.foundationId})\n" +
            "\tAND (0=:dayId OR ass.day_id=:dayId)\n" +
            "\torder by ass.day_id, ata.seq\n" +
            "), b as (\t\n" +
            "select a.level, a.\"institutionName\", a.\"classRoomId\", a.\"classRoomName\", a.sex, a.\"subjectId\", a.\"subjectName\", count(a.id) \"jumlahJadwal\", sum(jumlah_jam) \"jumlahJam\", " +
            "string_agg(distinct a.day_id||';'||a.\"dayName\", ', ' order by a.day_id||';'||a.\"dayName\") as days\n" +
            "from a \n" +
            "group by a.\"institutionName\",  a.\"classRoomId\", a.\"classRoomName\", a.sex, a.\"subjectId\", a.\"subjectName\", a.level\n" +
            ")\n" +
            "select b.\"classRoomId\", b.\"classRoomName\", b.sex, b.\"subjectId\", b.\"subjectName\", b.\"jumlahJam\" , b.\"jumlahJadwal\", \n" +
            "regexp_replace(b.days,'\\d+;','','g') days, b.\"institutionName\" " +
            "from b order by b.level", nativeQuery = true)
    public List<ScheduleTeacherSubjectListQuery> findScheduleTeacherSubjectList(@Param("teacherId") String teacherId,@Param("dayId") Integer dayId);
}
