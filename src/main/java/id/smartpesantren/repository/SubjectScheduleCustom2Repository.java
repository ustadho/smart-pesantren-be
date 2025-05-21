package id.smartpesantren.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class SubjectScheduleCustom2Repository {
    @PersistenceContext
    private EntityManager entityManager;

    public String findAllSchedules(String foundationId, String classRoomId, String timeZone) {
        String sql = "WITH schedule_data AS (\n" +
                "    SELECT \n" +
                "        cr.id AS class_room_id,\n" +
                "        act.id AS activity_id,\n" +
                "        CONCAT('Jam ke-', act.seq) AS activity_name,\n" +
                "        act.start_time,\n" +
                "        act.end_time,\n" +
                "        act.seq AS activity_seq,\n" +
                "        d.id AS day_id,\n" +
                "        d.name AS day_name,\n" +
                "        sch.id AS schedule_id,\n" +
                "        string_agg(distinct sst.subject_id, ', ') subject_ids,\n" +
                "        string_agg(distinct sub.name, ', ') AS subject_names,\n" +
                "        string_agg(distinct sst.teacher_id, ', ') teacher_id,\n" +
                "        string_agg(distinct p.name, ', ') AS teacher_names\n" +
                "    FROM ac_activity_time act\n" +
                "    JOIN ac_class_room cr on cr.id=:classRoomId and act.sex=cr.sex \n" +
                "    CROSS JOIN m_day d\n" +
                "    LEFT JOIN ac_subject_schedule sch \n" +
                "        ON sch.activity_time_id = act.id AND sch.day_id = d.id AND sch.class_room_id=:classRoomId\n" +
                "    LEFT JOIN ac_subject_schedule_teacher sst \n" +
                "        ON sch.id = sst.schedule_id\n" +
                "    LEFT JOIN person_data p \n" +
                "        ON p.id = sst.teacher_id\n" +
                "    LEFT JOIN ac_subject sub \n" +
                "        ON sub.id = sst.subject_id\n" +
                "    where act.foundation_id  = :foundationId \n" +
                "    and act.institution_id = cr.institution_id    group by cr.id,\n" +
                "        act.id,\n" +
                "        CONCAT('Jam ke-', act.seq),\n" +
                "        act.start_time,\n" +
                "        act.end_time,\n" +
                "        act.seq,\n" +
                "        d.id,\n" +
                "        d.name,\n" +
                "        sch.id,\n" +
                "        sub.id,\n" +
                "        sub.name \n" +
                "    order by act.start_time, act.seq, case when d.id=0 then 7 else d.id end \n" +
                ")\n" +
                "SELECT json_agg(\n" +
                "           json_build_object(\n" +
                "               'classRoomId', sd.class_room_id,\n" +
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
                "                                  'classRoomId', sdi.class_room_id,\n" +
                "                                  'activityTimeId', sdi.activity_id,\n" +
                "                                  'subject', sdi.subject_names,\n" +
                "                                  'teacher', sdi.teacher_names,\n" +
                "                                  'subjects', (\n" +
                "                                       SELECT COALESCE(json_agg(\n" +
                "                                           json_build_object(\n" +
                "                                               'id', asst.id,\n" +
                "                                               'subjectId', asst.subject_id,\n" +
                "                                               'subjectName', coalesce(as2.name,''),\n" +
                "                                               'teacherId', asst.teacher_id,\n" +
                "                                               'teacherName', coalesce(pd.name,'')\n" +
                "                                           )\n" +
                "                                       ), '[]'::::json)\n" +
                "                                       from ac_subject_schedule_teacher asst\n" +
                "                                       left join ac_subject2 as2 on as2.id=asst.subject_id\n" +
                "                                       left join person_data pd on pd.id=asst.teacher_id\n" +
                "                                       where asst.schedule_id = sdi.schedule_id\n" +
                "                                  )\n" +
                "                              )\n" +
                "                          )\n" +
                "                   FROM schedule_data sdi\n" +
                "                   WHERE sdi.activity_id = sd.activity_id\n" +
                "               )\n" +
                "           )\n" +
                "       )::::text AS result\n" +
                "FROM (\n" +
                "    SELECT DISTINCT activity_id, class_room_id, activity_name, activity_seq, start_time, end_time\n" +
                "    FROM schedule_data\n" +
                "    order by start_time, activity_seq\n" +
                ") sd ";
        System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("foundationId", foundationId);
        query.setParameter("classRoomId", classRoomId);
//        query.setParameter("timeZone", timeZone);
        Object result = query.getSingleResult();

        return result != null ? result.toString() : "[]";
    }

    public String findAllSchedulePerDay(String classRoomId, String timeZone) {
        String sql = "SELECT cast(json_agg(data) as text) AS result\n" +
                "FROM (\n" +
                "  SELECT json_build_object(\n" +
                "    'dayId', md.id,\n" +
                "    'dayName', md.name,\n" +
                "    'schedules', COALESCE((\n" +
                "      SELECT json_agg(\n" +
                "        json_build_object(\n" +
                "          'id', ass.id,\n" +
                "          'dayId', ass.day_id,\n" +
                "          'dayName', md.name,\n" +
                "          'classRoomId', ass.class_room_id,\n" +
                "          'activityStartId', ass.activity_time_start_id,\n" +
                "          'activityStartTime', at1.start_time,\n" +
                "          'activityEndId', ass.activity_time_end_id,\n" +
                "          'activityEndTime', at2.end_time,\n" +
                "          'duration', COALESCE(ass.duration, 0),\n" +
                "          'subjectTeachers', COALESCE((\n" +
                "            SELECT json_agg(\n" +
                "              json_build_object(\n" +
                "                'id', asst.id,\n" +
                "                'teacherId', asst.teacher_id,\n" +
                "                'teacherName', pd.name,\n" +
                "                'subjectId', asst.subject_id,\n" +
                "                'subjectName', s.name\n" +
                "              )\n" +
                "            )\n" +
                "            FROM ac_subject_schedule_teacher asst\n" +
                "            JOIN person_data pd ON pd.id = asst.teacher_id\n" +
                "            JOIN ac_subject s ON s.id = asst.subject_id \n" +
                "            WHERE asst.schedule_id = ass.id\n" +
                "          ), cast('[]' as json))\n" +
                "        )\n" +
                "      )\n" +
                "      FROM ac_subject_schedule ass\n" +
                "      LEFT JOIN ac_activity_time at1 ON at1.id = ass.activity_time_start_id\n" +
                "      LEFT JOIN ac_activity_time at2 ON at2.id = ass.activity_time_end_id\n" +
                "      WHERE ass.day_id = md.id\n" +
                "      AND ass.class_room_id = :classRoomId\n" +
                "      AND ass.deleted_date is null\n" +
                "    ), cast('[]' as json))\n" +
                "  ) AS data\n" +
                "  FROM m_day md\n" +
                "  ORDER BY CASE WHEN md.id = 0 THEN 9 ELSE md.id END\n" +
                ") days;";
        System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("classRoomId", classRoomId);
//        query.setParameter("timeZone", timeZone);
        Object result = query.getSingleResult();

        return result != null ? result.toString() : "[]";
    }

}
