package id.smartpesantren.repository;

import id.smartpesantren.dto.*;
import id.smartpesantren.entity.SubjectSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectScheduleRepository extends JpaRepository<SubjectSchedule, String> {
    @Query(value = "select distinct pd.id, pd.name   \n" +
            "from ac_subject_schedule ass\n" +
            "join ac_class_room acr on acr.id = ass.class_room_id\n" +
            "join ac_subject_schedule_teacher acrt on acrt.schedule_id = ass.id\n" +
            "join person_data pd on pd.id=acrt.teacher_id \n" +
            "where acr.academic_year_id = :academicYear\n" +
            "and ass.day_id = EXTRACT('DOW' FROM CURRENT_DATE)\n" +
            "order by pd.name", nativeQuery = true)
    public List<PersonSimpleDTO> findAllTeacherScheduleToday(@Param("academicYear") String academicYear);

    @Query(value = "select ass.class_room_id \"classRoomId\", acr.name \"classRoomName\", asst.subject_id \"subjectId\", asst.id \"scheduleId\", \n" +
            "as2.name \"subjectName\", aat1.start_time \"startTime\", aat2.end_time \"endTime\"\n" +
            "from ac_subject_schedule_teacher asst \n" +
            "join ac_subject_schedule ass on ass.id=asst.schedule_id \n" +
            "join ac_class_room acr on acr.id=ass.class_room_id \n" +
            "join ac_subject as2 on as2.id = asst.subject_id \n" +
            "join ac_activity_time aat1 on aat1.id = ass.activity_time_start_id \n" +
            "join ac_activity_time aat2 on aat2.id=ass.activity_time_end_id\n" +
            "and ass.day_id = EXTRACT('DOW' FROM CURRENT_DATE)\n" +
            "and asst.teacher_id = :teacherId\n" +
            "order by aat1.start_time ", nativeQuery = true)
    List<MySchedule2DTO> findTeacherScheduleToday(@Param("teacherId") String teacherId);

    @Query(value = "select st.schedule_id \"scheduleId\", ass.day_id \"dayId\", md.\"name\" \"dayName\", coalesce(acr.name,'') \"classRoomName\",\n" +
            "coalesce(i.\"name\") \"institutionName\", st.subject_id \"subjectId\", coalesce(as2.\"name\",'') \"subjectName\", \n" +
            "aat.start_time \"startTime\", aat2.end_time \"endTime\", coalesce(ass.duration,1)  duration, '' teachers\n" +
            "from ac_subject_schedule_teacher st\n" +
            "join ac_subject_schedule ass on ass.id=st.schedule_id\n" +
            "join m_day md on md.id=ass.day_id \n" +
            "join ac_class_room acr on acr.id=ass.class_room_id \n" +
            "join institution i on i.id=acr.institution_id \n" +
            "join ac_subject as2 on as2.id=st.subject_id \n" +
            "join ac_activity_time aat on aat.id=ass.activity_time_start_id \n" +
            "join ac_activity_time aat2 on aat2.id=ass.activity_time_end_id  \n" +
            "where st.teacher_id=:teacherId  \n" +
            "order by ass.day_id,  aat.start_time  ", nativeQuery = true)
    List<MyScheduleWeeklyDTO> findAllMyWeeklySchedule(@Param("teacherId") String teacherId);
}
