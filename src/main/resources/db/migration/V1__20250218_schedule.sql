WITH ordered_schedule AS (
    SELECT
        ass.class_room_id,
        ass.subject_id,
        ass.id AS schedule_id,
        aat.start_time,
        aat.end_time,
        ass.day_id,
        string_agg(pd."name", ', ') AS teachers
    FROM ac_subject_schedule ass
    JOIN ac_activity_time aat ON ass.activity_time_id = aat.id
    JOIN ac_subject_schedule_teacher asst ON asst.schedule_id = ass.id
    JOIN person_data pd ON pd.id = asst.teacher_id
    GROUP BY ass.class_room_id, ass.subject_id, ass.id, aat.start_time, aat.end_time, ass.day_id
),
merged_schedule AS (
    SELECT DISTINCT ON (os.class_room_id, os.subject_id, os.day_id)
        os.day_id,
        os.class_room_id,
        os.subject_id,
        os.schedule_id,  -- Mengambil schedule_id dari jadwal pertama
        MIN(os.start_time) OVER (PARTITION BY os.class_room_id, os.subject_id, os.day_id) AS start_time,
        MAX(os.end_time) OVER (PARTITION BY os.class_room_id, os.subject_id, os.day_id) AS end_time,
        os.teachers,
        -- Menghitung jumlah sesi (baris) yang tergabung
        COUNT(*) OVER (PARTITION BY os.class_room_id, os.subject_id, os.day_id) AS duration
    FROM ordered_schedule os
    ORDER BY os.class_room_id, os.subject_id, os.day_id, os.start_time
)
SELECT
    ay.name AS academic_year_name, -- Bisa diganti dengan tahun akademik dinamis jika ada
    acr.academic_year_id,
    ms.class_room_id,
    acr."name" AS class_room_name,
    ms.day_id,
    d.name AS day_name,
    ms.subject_id,
    s.name AS subject_name,
    ms.schedule_id,
    ms.start_time,
    ms.end_time,
    ms.duration,  -- Menampilkan jumlah sesi yang digabungkan
    ms.teachers
FROM merged_schedule ms
JOIN ac_subject s ON s.id = ms.subject_id
JOIN ac_class_room acr ON acr.id = ms.class_room_id
JOIN academic_year ay ON ay.id = acr.academic_year_id
JOIN m_day d ON d.id = ms.day_id
ORDER BY d.id, acr."name", ms.start_time;

-- public.vw_schedule_all source

CREATE OR REPLACE VIEW public.vw_schedule_all
AS WITH ordered_schedule AS (
         SELECT ass.class_room_id,
            ass.subject_id,
            ass.id AS schedule_id,
            aat.start_time,
            aat.end_time,
            ass.day_id,
            string_agg(pd.name::text, ', '::text) AS teachers
           FROM ac_subject_schedule ass
             JOIN ac_activity_time aat ON ass.activity_time_id::text = aat.id::text
             JOIN ac_subject_schedule_teacher asst ON asst.schedule_id::text = ass.id::text
             JOIN person_data pd ON pd.id::text = asst.teacher_id::text
          GROUP BY ass.class_room_id, ass.subject_id, ass.id, aat.start_time, aat.end_time, ass.day_id
        ), merged_schedule AS (
         SELECT DISTINCT ON (os.class_room_id, os.subject_id, os.day_id) os.day_id,
            os.class_room_id,
            os.subject_id,
            os.schedule_id,
            min(os.start_time) OVER (PARTITION BY os.class_room_id, os.subject_id, os.day_id) AS start_time,
            max(os.end_time) OVER (PARTITION BY os.class_room_id, os.subject_id, os.day_id) AS end_time,
            os.teachers,
            count(*) OVER (PARTITION BY os.class_room_id, os.subject_id, os.day_id) AS duration
           FROM ordered_schedule os
          ORDER BY os.class_room_id, os.subject_id, os.day_id, os.start_time
        )
 SELECT ay.name AS academic_year_name,
    acr.academic_year_id,
    ms.class_room_id,
    acr.name AS class_room_name,
    ms.day_id,
    d.name AS day_name,
    ms.subject_id,
    s.name AS subject_name,
    ms.schedule_id,
    ms.start_time,
    ms.end_time,
    ms.duration,
    ms.teachers
   FROM merged_schedule ms
     JOIN ac_subject s ON s.id::text = ms.subject_id::text
     JOIN ac_class_room acr ON acr.id::text = ms.class_room_id::text
     JOIN academic_year ay ON ay.id::text = acr.academic_year_id::text
     JOIN m_day d ON d.id = ms.day_id
  ORDER BY d.id, acr.name, ms.start_time;


