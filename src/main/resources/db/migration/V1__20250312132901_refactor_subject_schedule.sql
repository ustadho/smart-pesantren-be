drop view vw_schedule_all ;
drop table ac_presence_kbm_student;
drop table ac_presence_kbm;
drop table ac_setting_schedule_teacher;
drop table ac_setting_schedule;
drop table ac_subject_schedule_teacher;
drop table ac_subject_schedule;
alter table ac_subject_schedule_teacher2 rename to ac_subject_schedule_teacher;
alter table ac_subject_schedule2 rename to ac_subject_schedule;
delete from ac_subject_schedule_history;

INSERT INTO ac_subject_schedule_history
(id, created_by, created_date, log_activity, subject_schedule_id, class_room_id, day_id, subject_id, teacher_id, activity_time_end_id, activity_time_start_id, duration)
select uuid_generate_v4(), h.created_by, h.created_date, 'INSERT', h.id, h.class_room_id, h.day_id, st.subject_id, st.teacher_id, h.activity_time_end_id, h.activity_time_start_id, h.duration
from ac_subject_schedule h
join ac_subject_schedule_teacher st on st.schedule_id=h.id


