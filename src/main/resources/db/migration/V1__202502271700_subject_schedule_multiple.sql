CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

drop VIEW public.vw_schedule_all;
drop table ac_subject_schedule_teacher;

insert into ac_subject_schedule_teacher(id, schedule_id, subject_id, teacher_id)
select uuid_generate_v4(), schedule_id, subject_id, teacher_id
from ac_subject_schedule_teacher_backup;



-- public.vw_schedule_all source

CREATE OR REPLACE VIEW public.vw_schedule_all
AS
WITH ordered_schedule AS (
         SELECT ass.class_room_id,
            ass.id AS schedule_id,
            aat.start_time,
            aat.end_time,
            ass.day_id,
            string_agg(s.name::text, ', '::text) AS subjects,
            string_agg(pd.name::text, ', '::text) AS teachers
           FROM ac_subject_schedule ass
             JOIN ac_activity_time aat ON ass.activity_time_id::text = aat.id::text
             JOIN ac_subject_schedule_teacher asst ON asst.schedule_id::text = ass.id::text
             JOIN person_data pd ON pd.id::text = asst.teacher_id::text
             join ac_subject s on s.id=asst.subject_id
          GROUP BY ass.class_room_id, ass.id, aat.start_time, aat.end_time, ass.day_id
        ), merged_schedule AS (
         SELECT DISTINCT ON (os.class_room_id, os.day_id) os.day_id,
            os.class_room_id,
            os.schedule_id,
            min(os.start_time) OVER (PARTITION BY os.class_room_id, os.subjects, os.day_id) AS start_time,
            max(os.end_time) OVER (PARTITION BY os.class_room_id, os.subjects, os.day_id) AS end_time,
            os.subjects,
            os.teachers,
            count(*) OVER (PARTITION BY os.class_room_id, os.subjects, os.day_id) AS duration
           FROM ordered_schedule os
          ORDER BY os.class_room_id, os.day_id, os.start_time
        )
 SELECT ay.name AS academic_year_name,
    acr.academic_year_id,
    i.name AS institution_name,
    ms.class_room_id,
    acr.name AS class_room_name,
    ms.day_id,
    d.name AS day_name,
    ms.schedule_id,
    ms.start_time,
    ms.end_time,
    ms.duration,
    ms.subjects,
    ms.teachers
   FROM merged_schedule ms
     JOIN ac_class_room acr ON acr.id::text = ms.class_room_id::text
     JOIN academic_year ay ON ay.id::text = acr.academic_year_id::text
     JOIN institution i ON i.id::text = acr.institution_id::text
     JOIN m_day d ON d.id = ms.day_id
  ORDER BY d.id, acr.name, ms.start_time;

 WITH schedule_data AS (
    SELECT
        cr.id AS class_room_id,
        act.id AS activity_id,
        CONCAT('Jam ke-', act.seq) AS activity_name,
        act.start_time,
        act.end_time,
        act.seq AS activity_seq,
        d.id AS day_id,
        d.name AS day_name,
        sch.id AS schedule_id,
        string_agg(distinct sst.subject_id, ', ') subject_ids,
        string_agg(distinct sub.name, ', ') AS subject_names,
        string_agg(distinct sst.teacher_id, ', ') teacher_id,
        string_agg(distinct p.name, ', ') AS teacher_names
    FROM ac_activity_time act
    JOIN ac_class_room cr on cr.id=:classRoomId and act.sex=cr.sex
    CROSS JOIN m_day d
    LEFT JOIN ac_subject_schedule sch
        ON sch.activity_time_id = act.id AND sch.day_id = d.id AND sch.class_room_id=:classRoomId
    LEFT JOIN ac_subject_schedule_teacher sst
        ON sch.id = sst.schedule_id
    LEFT JOIN person_data p
        ON p.id = sst.teacher_id
    LEFT JOIN ac_subject sub
        ON sub.id = sst.subject_id
    where act.foundation_id  = :foundationId
    and act.institution_id = cr.institution_id    group by cr.id,
        act.id,
        CONCAT('Jam ke-', act.seq),
        act.start_time,
        act.end_time,
        act.seq,
        d.id,
        d.name,
        sch.id,
        sub.id,
        sub.name
    order by act.start_time, act.seq, case when d.id=0 then 7 else d.id end
)
SELECT json_agg(
           json_build_object(
               'classRoomId', sd.class_room_id,
               'activityId', sd.activity_id,
               'activityName', sd.activity_name,
               'startTime', sd.start_time,
               'endTime', sd.end_time,
               'seq', sd.activity_seq,
               'days', (
                   SELECT json_agg(
                              json_build_object(
                                  'dayId', sdi.day_id,
                                  'dayName', sdi.day_name,
                                  'id', sdi.schedule_id,
                                  'classRoomId', sdi.class_room_id,
                                  'activityTimeId', sdi.activity_id,
                                  'subject', sdi.subject_names,
                                  'teacher', sdi.teacher_names,
                                  'subjects', (
                                       SELECT COALESCE(json_agg(
                                           json_build_object(
                                               'id', asst.id,
                                               'subjectId', asst.subject_id,
                                               'subjectName', coalesce(as2.name,''),
                                               'teacherId', asst.teacher_id,
                                               'teacherName', coalesce(pd.name,'')
                                           )
                                       ), '[]'::json)
                                       from ac_subject_schedule_teacher asst
                                       left join ac_subject as2 on as2.id=asst.subject_id
                                       left join person_data pd on pd.id=asst.teacher_id
                                       where asst.schedule_id = sdi.schedule_id
                                  )
                              )
                          )
                   FROM schedule_data sdi
                   WHERE sdi.activity_id = sd.activity_id
               )
           )
       )::text AS result
FROM (
    SELECT DISTINCT activity_id, class_room_id, activity_name, activity_seq, start_time, end_time
    FROM schedule_data
    order by start_time, activity_seq
) sd

