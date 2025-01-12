alter table hr_working_time drop column check_in_time;
alter table hr_working_time drop column check_out_time;
alter table hr_working_time drop column scan_end_check_in_time;
alter table hr_working_time drop column scan_end_check_out_time;
alter table hr_working_time drop column scan_start_check_in_time;
alter table hr_working_time drop column scan_start_check_out_time;

alter table ac_activity_time drop column end_time;
alter table ac_activity_time drop column start_time;
