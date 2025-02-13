package id.smartpesantren.repository;

import id.smartpesantren.dto.PersonSimpleDTO;
import id.smartpesantren.dto.PresenceSubjectStudentDTO;
import id.smartpesantren.dto.SubjectScheduleClassRoomDTO;
import id.smartpesantren.entity.SubjectSchedule;
import id.smartpesantren.entity.User;
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
            "join person_data pd on pd.id=ass.teacher_id \n" +
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
}
