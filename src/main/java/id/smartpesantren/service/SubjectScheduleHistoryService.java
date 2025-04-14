package id.smartpesantren.service;

import id.smartpesantren.entity.*;
import id.smartpesantren.repository.SubjectScheduleHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectScheduleHistoryService {
    @Autowired
    SubjectScheduleHistoryRepository subjectScheduleHistoryRepository;


    public SubjectScheduleHistory fromOrigin(SubjectScheduleTeacher s) {
        SubjectScheduleHistory sh = new SubjectScheduleHistory();
        sh.setSubject(s.getSubject());
        sh.setActivityTimeStart(s.getSchedule().getActivityTimeStart());
        sh.setActivityTimeEnd(s.getSchedule().getActivityTimeEnd());
        sh.setDay(s.getSchedule().getDay());
        sh.setClassRoom(s.getSchedule().getClassRoom());
        sh.setSubjectScheduleId(s.getId());
        sh.setTeacher(s.getTeacher());
        sh.setSubjectScheduleId(s.getId());
        return sh;
    }
}
