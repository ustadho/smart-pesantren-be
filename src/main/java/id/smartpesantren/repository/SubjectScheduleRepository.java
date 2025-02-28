package id.smartpesantren.repository;

import id.smartpesantren.dto.MyScheduleDTO;
import id.smartpesantren.dto.MyScheduleWeeklyDTO;
import id.smartpesantren.dto.PersonSimpleDTO;
import id.smartpesantren.dto.SubjectScheduleClassRoomDTO;
import id.smartpesantren.entity.SubjectSchedule;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubjectScheduleRepository extends JpaRepository<SubjectSchedule, String> {

    @Query(value = "WITH schedule_data AS (\n" +
            "    SELECT \n" +
            "        act.id AS activity_id,\n" +
            "        CONCAT('Jam ke-', act.seq) AS activity_name,\n" +
            "        TO_CHAR(act.start_time, 'HH24:MI') AS start_time,\n" +
            "        TO_CHAR(act.end_time, 'HH24:MI') AS end_time,\n" +
            "        d.id AS day_id,\n" +
            "        d.name AS day_name,\n" +
            "        sch.id AS schedule_id,\n" +
            "        sub.id AS subject_id,\n" +
            "        sub.name AS subject_name,\n" +
            "        p.id AS teacher_id,\n" +
            "        p.name AS teacher_name\n" +
            "    FROM ac_activity_time act\n" +
            "    CROSS JOIN m_day d\n" +
            "    LEFT JOIN ac_subject_schedule sch \n" +
            "        ON sch.activity_time_id = act.id AND sch.day_id = d.id \n" +
            "    LEFT JOIN ac_subject sub \n" +
            "        ON sub.id = sch.subject_id\n" +
            "    LEFT JOIN person_data p \n" +
            "        ON p.id = sch.teacher_id\n" +
            "    where act.foundation_id  = ?#{principal.foundationId} \n" +
            "    and act.institution_id = :institutionId \n" +
            ")\n" +
            "SELECT json_agg(\n" +
            "           json_build_object(\n" +
            "               'activityId', sd.activity_id,\n" +
            "               'activityName', sd.activity_name,\n" +
            "               'startTime', sd.start_time,\n" +
            "               'endTime', sd.end_time,\n" +
            "               'days', (\n" +
            "                   SELECT json_agg(\n" +
            "                              json_build_object(\n" +
            "                                  'dayId', sdi.day_id,\n" +
            "                                  'dayName', sdi.day_name,\n" +
            "                                  'sceduleId', sdi.schedule_id,\n" +
            "                                  'subjectId', sdi.subject_id,\n" +
            "                                  'subjectName', sdi.subject_name,\n" +
            "                                  'teacherId', sdi.teacher_id,\n" +
            "                                  'teacherName', sdi.teacher_name\n" +
            "                              )\n" +
            "                          )\n" +
            "                   FROM schedule_data sdi\n" +
            "                   WHERE sdi.activity_id = sd.activity_id\n" +
            "               )\n" +
            "           )\n" +
            "       ) AS result\n" +
            "FROM (\n" +
            "    SELECT DISTINCT activity_id, activity_name, start_time, end_time\n" +
            "    FROM schedule_data\n" +
            ") sd;\n",
            nativeQuery = true)
    public String findAllSchedules(@Param("institutionId") String institutionId);

    @Query(value = "select distinct pd.id, pd.name   \n" +
            "from ac_subject_schedule ass\n" +
            "join ac_class_room acr on acr.id = ass.class_room_id\n" +
            "join ac_subject_schedule_teacher acrt on acrt.schedule_id = ass.id\n" +
            "join person_data pd on pd.id=acrt.teacher_id \n" +
            "where acr.academic_year_id = :academicYear\n" +
            "and ass.day_id = EXTRACT('DOW' FROM CURRENT_DATE)\n" +
            "order by pd.name", nativeQuery = true)
    public List<PersonSimpleDTO> findAllTeacherScheduleToday(@Param("academicYear") String academicYear);

    @Query(value = "select ass.id, s.name \"subjectName\", acr.name \"classRoom\", aat.start_time \"startTime\" , aat.end_time \"endTime\"\n" +
            "from ac_subject_schedule ass \n" +
            "join ac_activity_time aat on aat.id=ass.activity_time_id \n" +
            "join ac_subject s on s.id=ass.subject_id  \n" +
            "join ac_class_room acr on acr.id=ass.class_room_id \n" +
            "where ass.teacher_id = :teacher\n" +
            "and ass.day_id = EXTRACT('DOW' FROM CURRENT_DATE)\n" +
            "order by aat.start_time ", nativeQuery = true)
    public List<SubjectScheduleClassRoomDTO> findSubjectScheduleClassRoomByTeacherId(@Param("teacher") String teacher);

    @EntityGraph(attributePaths = {"teachers"})
    Optional<SubjectSchedule> findOneWithTeacherById(String id);

    @Query(value =
            "WITH schedule_data AS (\n" +
                    "    SELECT \n" +
                    "        sst.schedule_id AS id,\n" +
                    "        aat.start_time::::TIME AS start_time,\n" +
                    "        aat.end_time::::TIME AS end_time,\n" +
                    "        sst.subject_id,\n" +
                    "        sub.name AS subject_name,\n" +
                    "        LAG(aat.end_time::::TIME) OVER (PARTITION BY sst.teacher_id, sst.subject_id ORDER BY aat.start_time::::TIME) AS prev_end_time,\n" +
                    "        ss.class_room_id, cr.name class_room_name\n" +
                    "    FROM ac_subject_schedule_teacher sst\n" +
                    "    JOIN ac_subject_schedule ss ON sst.schedule_id = ss.id\n" +
                    "    JOIN ac_activity_time aat ON ss.activity_time_id = aat.id\n" +
                    "    JOIN ac_subject sub ON sst.subject_id = sub.id\n" +
                    "    join ac_class_room cr on cr.id=ss.class_room_id\n" +
                    "    where sst.teacher_id=:teacherId\n" +
                    "    and cr.academic_year_id = (select id from academic_year ay where foundation_id=?#{principal.foundationId} and ay.is_default = true limit 1)\n" +
                    "    and ss.day_id = extract(dow from current_date)\n" +
                    "    ORDER BY subject_name, start_time\n" +
                    "),\n" +
                    "merged_schedules AS (\n" +
                    "    SELECT \n" +
                    "        id, start_time, end_time, subject_id, subject_name, class_room_id, class_room_name,\n" +
                    "        SUM(CASE \n" +
                    "            WHEN prev_end_time = start_time THEN 0 ELSE 1 \n" +
                    "        END) OVER (PARTITION BY subject_name, class_room_id ORDER BY start_time) AS group_id\n" +
                    "    FROM schedule_data\n" +
                    ")\n" +
                    "SELECT \n" +
                    "    MIN(id) AS \"subjectScheduleId\",\n" +
                    "    MIN(start_time) AS \"startTime\",\n" +
                    "    MAX(end_time) AS \"endTime\",\n" +
                    "    subject_id \"subjectId\",\n" +
                    "    subject_name \"subjectName\",\n" +
                    "    class_room_id \"classRoomId\", \n" +
                    "    class_room_name \"classRoomName\"\n" +
                    "FROM merged_schedules\n" +
                    "GROUP BY group_id, subject_id, subject_name, class_room_id, class_room_name\n" +
                    "ORDER BY \"startTime\"", nativeQuery = true)
    public List<MyScheduleDTO> findTeacherScheduleToday(@Param("teacherId") String id);

    @Query(value = "select vw.schedule_id \"scheduleId\", vw.day_id \"dayId\", vw.day_name \"dayName\", vw.institution_name \"institutionName\", vw.class_room_name \"classRoomName\", vw.subject_id \"subjectId\", vw.subject_name \"subjectName\", \n" +
            "vw.start_time \"startTime\", vw.end_time \"endTime\", vw.duration, vw.teachers\n" +
            "from vw_schedule_all vw \n" +
            "join ac_subject_schedule_teacher ast on ast.schedule_id=vw.schedule_id \n" +
            "join academic_year ay on ay.id = vw.academic_year_id \n" +
            "and ay.is_default = true\n" +
            "where ast.teacher_id =:teacherId \n" +
            "order by vw.day_id, vw.start_time\n", nativeQuery = true)
    public List<MyScheduleWeeklyDTO> findAllMyWeeklySchedule(@Param("teacherId") String id);
}
