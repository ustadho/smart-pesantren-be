package id.smartpesantren.service;

import id.smartpesantren.entity.*;
import id.smartpesantren.repository.PresenceKBMRepository;
import id.smartpesantren.repository.SubjectScheduleRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.ClassRoomStudentVM;
import id.smartpesantren.web.rest.vm.ClassRoomStudentVMDetail;
import id.smartpesantren.web.rest.vm.PresenceKbmVM;
import id.smartpesantren.web.rest.vm.PresenceKbmVMStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

@Service
public class PresenceKBMService {
    @Autowired
    public PresenceKBMRepository presenceKBMRepository;

    @Autowired
    ClassRoomStudentService classRoomStudentService;

    @Autowired
    SubjectScheduleRepository subjectScheduleRepository;


    Logger logger = LoggerFactory.getLogger(PresenceKBMService.class);

    public PresenceKbmVM findByPresenceDateAndSchedule(String date, String scheduleId) {
        PresenceKbmVM vm = null;
        try {
            Date presenceDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            PresenceKBM p = presenceKBMRepository.findByPresenceDateAndSubjectSchedule(
                    presenceDate,
                    new SubjectSchedule(scheduleId)
            );
            if(p != null) {
                vm = toVM(p);
            } else {
                vm = new PresenceKbmVM();
                vm.setPresenceDate(presenceDate);
                vm.setSubjectScheduleId(scheduleId);

                SubjectSchedule ss = subjectScheduleRepository.findById(scheduleId).get();
                if(ss != null) {
                    ClassRoomStudentVM svm = classRoomStudentService.findById(ss.getClassRoom().getId());
                    for(ClassRoomStudentVMDetail vd: svm.getStudents()) {
                        PresenceKbmVMStudent kbs = new PresenceKbmVMStudent();
                        kbs.setStudentId(vd.getStudentId());
                        kbs.setStudentName(vd.getStudentName());
                        kbs.setStudentNis(vd.getStudentNis());
                        kbs.setStudentNisn(vd.getStudentNisn());
                        kbs.setPresenceStatusId(1);
                    }
                }
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return vm;
    }

    private PresenceKbmVM toVM(PresenceKBM p) {
        PresenceKbmVM vm = new PresenceKbmVM();
        vm.setId(p.getId());
        vm.setPresenceDate(p.getPresenceDate());
        vm.setSubjectScheduleId(p.getSubjectSchedule().getId());
        vm.setTeacherId(p.getTeacher().getId());
        vm.setDescription(p.getDescription());
        for(PresenceKBMStudent s: p.getStudents()) {
            PresenceKbmVMStudent vmd = new PresenceKbmVMStudent();
            vmd.setId(s.getId());
            vmd.setStudentId(s.getStudent().getId());
            vmd.setStudentName(s.getStudent().getName());
            vmd.setStudentNis(s.getStudent().getNis());
            vmd.setStudentNisn(s.getStudent().getNisn());
            vm.getStudents().add(vmd);
        }

        return vm;
    }

    public PresenceKbmVM createOrUpdate(PresenceKbmVM vm) {

        return vm;
    }

    PresenceKBM fromVM(PresenceKbmVM vm) {
        PresenceKBM p = null;
        if (vm.getId() != null) {
            p = presenceKBMRepository.findById(vm.getId()).get();
        } else {
            p = new PresenceKBM();
            p.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        }
        p.setPresenceDate(vm.getPresenceDate());
        p.setSubjectSchedule(new SubjectSchedule(vm.getSubjectScheduleId()));
        p.setTeacher(new PersonData(vm.getTeacherId()));
        p.setDescription(vm.getDescription());

        for (Iterator<PresenceKBMStudent> iterator = p.getStudents().iterator(); iterator.hasNext(); ) {
            PresenceKBMStudent d = iterator.next();
            boolean used = false;
            for (PresenceKbmVMStudent ri : vm.getStudents()) {
                if (ri.getId() != null && ri.getId().equalsIgnoreCase(d.getId())) {
                    used = true;
                    break;
                }
            }
            if (!used) {
                iterator.remove();
            }
        }

        for (PresenceKbmVMStudent d : vm.getStudents()) {
            logger.debug("req.getItems.student ==>[{}]", d.getStudentId());
            PresenceKBMStudent det = null;

            if (p.getId() == null) {
                det = new PresenceKBMStudent();
            } else {
                if (d.getId() == null) {
                    det = new PresenceKBMStudent();
                } else {
                    // Check if existing detail needs deletion
                    boolean existingDetailFound = false;
                    for (PresenceKBMStudent existingDetail : p.getStudents()) {
                        if (existingDetail.getId().equals(d.getId())) {
                            det = existingDetail;
                            existingDetailFound = true;
                            break;
                        }
                    }
                    if (!existingDetailFound) {
                        System.out.println("detail not exists");
                    }
                }
            }
            det.setId(d.getId());
            det.setStudent(new Student(d.getStudentId()));
            det.setPresenceStatus(new PresenceStatus(d.getPresenceStatusId()));
            det.setNote(d.getNote());

            if(det.getId() == null) {
                p.getStudents().add(det);
            }
        }
        return p;
    }
}

