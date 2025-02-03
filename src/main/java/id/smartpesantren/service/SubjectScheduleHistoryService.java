package id.smartpesantren.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.smartpesantren.dto.ActivityScheduleDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.SubjectScheduleCustomRepository;
import id.smartpesantren.repository.SubjectScheduleHistoryRepository;
import id.smartpesantren.repository.SubjectScheduleRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.SubjectScheduleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectScheduleHistoryService {
    @Autowired
    SubjectScheduleHistoryRepository subjectScheduleHistoryRepository;


    public SubjectScheduleHistory fromOrigin(SubjectSchedule s) {
        SubjectScheduleHistory sh = new SubjectScheduleHistory();
        sh.setSubject(s.getSubject());
        sh.setActivityTime(s.getActivityTime());
        sh.setDay(s.getDay());
        sh.setClassRoom(s.getClassRoom());
        sh.setSubjectScheduleId(s.getId());
        sh.setTeacher(s.getTeacher());
        sh.setSubjectScheduleId(s.getId());
        return sh;
    }
}
