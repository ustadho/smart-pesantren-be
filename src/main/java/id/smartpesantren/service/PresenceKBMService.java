package id.smartpesantren.service;

import id.smartpesantren.entity.*;
import id.smartpesantren.repository.PresenceKBMStudentRepository;
import id.smartpesantren.repository.SubjectScheduleRepository;
import id.smartpesantren.web.rest.vm.PresenceKbmVM;
import id.smartpesantren.web.rest.vm.PresenceKbmVMStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class PresenceKBMService {
    @Autowired
    public PresenceKBMStudentRepository presenceKBMStudentRepository;

    @Autowired
    ClassRoomStudentService classRoomStudentService;

    @Autowired
    SubjectScheduleRepository subjectScheduleRepository;


    Logger logger = LoggerFactory.getLogger(PresenceKBMService.class);


    public void createOrUpdate(PresenceKbmVM vm) {
        Iterable<PresenceKBM> p = fromVM(vm);
        presenceKBMStudentRepository.saveAll(p);
    }

    List<PresenceKBM> fromVM(PresenceKbmVM vm) {
        List<PresenceKBM> list = new ArrayList<>();
        for (Iterator<PresenceKbmVMStudent> iterator = vm.getStudents().iterator(); iterator.hasNext(); ) {
            PresenceKbmVMStudent d = iterator.next();
            PresenceKBM p;
            if(d.getId() != null) {
                p = presenceKBMStudentRepository.findById(d.getId()).get();
                if(p == null) {
                    p = new PresenceKBM();
                    p.setPresenceDate(new Date());
                }
            } else {
                p = new PresenceKBM();
                p.setPresenceDate(new Date());
            }
            p.setSubjectSchedule(new SubjectSchedule(vm.getSubjectScheduleId()));
            p.setTeacher(new PersonData(vm.getSubjectScheduleTeacherId()));
            p.setStudent(new Student(d.getStudentId()));
            p.setPresenceStatus(new PresenceStatus(d.getPresenceStatusId()));
            p.setNote(d.getNote());
            p.setAttachment(d.getAttachment());
            list.add(p);
        }

        return list;
    }
}

