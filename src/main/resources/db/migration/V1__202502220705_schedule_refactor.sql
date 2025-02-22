INSERT INTO public.ac_subject_schedule_teacher
(id, schedule_id, teacher_id)
select uuid_generate_v4(), schedule_id, teacher_id
from ac_subject_schedule_teacher_old ;

