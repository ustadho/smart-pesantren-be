package id.smartpesantren.repository;

import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SubjectScheduleCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public String findAllSchedules(String foundationId, String classRoomId, String timeZone) {
        String sql = "WITH schedule_data AS (\n" +
                "    SELECT \n" +
                "        act.id AS activity_id,\n" +
                "        CONCAT('Jam ke-', act.seq) AS activity_name,\n" +
                "        TO_CHAR((CURRENT_DATE + act.start_time):::: timestamp without time zone AT TIME ZONE :timeZone, 'HH24:MI') AS start_time,\n" +
                "        TO_CHAR((CURRENT_DATE + act.end_time):::: timestamp without time zone AT TIME ZONE :timeZone, 'HH24:MI') AS end_time,\n" +
                "        act.seq AS activity_seq,\n" +
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
                "        ON sch.activity_time_id = act.id AND sch.day_id = d.id AND sch.class_room_id=:classRoomId\n" +
                "    LEFT JOIN ac_subject sub \n" +
                "        ON sub.id = sch.subject_id\n" +
                "    LEFT JOIN person_data p \n" +
                "        ON p.id = sch.teacher_id\n" +
                "    where act.foundation_id  = :foundationId \n" +
                "    and act.institution_id = (select institution_id from ac_class_room where id=:classRoomId) \n" +
                "    order by act.start_time, act.seq, case when d.id=0 then 7 else d.id end \n" +
                ")\n" +
                "SELECT json_agg(\n" +
                "           json_build_object(\n" +
                "               'activityId', sd.activity_id,\n" +
                "               'activityName', sd.activity_name,\n" +
                "               'startTime', sd.start_time,\n" +
                "               'endTime', sd.end_time,\n" +
                "               'seq', sd.activity_seq,\n" +
                "               'days', (\n" +
                "                   SELECT json_agg(\n" +
                "                              json_build_object(\n" +
                "                                  'dayId', sdi.day_id,\n" +
                "                                  'dayName', sdi.day_name,\n" +
                "                                  'id', sdi.schedule_id,\n" +
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
                "       )::::text AS result\n" +
                "FROM (\n" +
                "    SELECT DISTINCT activity_id, activity_name, activity_seq, start_time, end_time\n" +
                "    FROM schedule_data\n" +
                "    order by start_time, activity_seq\n" +
                ") sd " +
                "\n";
        System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("foundationId", foundationId);
        query.setParameter("classRoomId", classRoomId);
        query.setParameter("timeZone", timeZone);
        Object result = query.getSingleResult();

        return result != null ? result.toString() : "[]";
    }
}
