package id.smartpesantren.service;

import id.smartpesantren.entity.*;
import id.smartpesantren.repository.PresenceKBMRepository;
import id.smartpesantren.repository.PresenceKBMStudentRepository;
import id.smartpesantren.repository.SubjectScheduleRepository;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.PresenceKbmVM;
import id.smartpesantren.web.rest.vm.PresenceKbmVMStudent;
import id.smartpesantren.web.rest.vm.PresenceKbmVMTeacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@Service
public class PresenceKBMService {
    @Autowired
    public PresenceKBMStudentRepository presenceKBMStudentRepository;

    @Autowired
    public PresenceKBMRepository presenceKBMRepository;

    @Autowired
    ClassRoomStudentService classRoomStudentService;

    @Autowired
    SubjectScheduleRepository subjectScheduleRepository;


    Logger logger = LoggerFactory.getLogger(PresenceKBMService.class);

    @Transactional
    public PresenceKbmVMTeacher createOrUpdatePresenceKBMTeacher(PresenceKbmVMTeacher vm) {
        PresenceKBM p = null;
        if(vm.getId() == null) {
            // find by subjectScheduleTeacher and presence date first
            Optional<PresenceKBM> check = presenceKBMRepository.findBySubjectScheduleTeacherAndPresenceDate(new SubjectScheduleTeacher(vm.getSubjectScheduleTeacherId()), vm.getPresenceDate());
            if(check.isPresent()) {
                p = check.get();
            } else {
                p = new PresenceKBM();
                p.setPresenceDate(LocalDate.now());
            }
        } else if (vm.getId() != null) {
            Optional<PresenceKBM> op = this.presenceKBMRepository.findById(vm.getId());
            if(op.isPresent()) {
                p = op.get();
            } else {
                throw new InternalServerErrorException("id absen tidak ditemukan");
            }
        }
        if(p == null) {
            p = new PresenceKBM();
        }
        p.setSubjectScheduleTeacher(new SubjectScheduleTeacher(vm.getSubjectScheduleTeacherId()));
        p.setTeacher(new PersonData(vm.getTeacherId()));
        p.setMateri(vm.getMateri());
        p.setFotoAbsen(vm.getFotoAbsen());
        p.setIndikator(vm.getIndikator());
        p.setPencapaian(vm.getPencapaian());
        p.setAttachment(vm.getAttachment());
        p.setPresenceStatus(new PresenceStatus(vm.getStatusId()));
        this.presenceKBMRepository.save(p);

        vm.setId(p.getId());
        return vm;
    }

    @Transactional
    public void createOrUpdate(PresenceKbmVM vm) {
        PresenceKBM p = null;
        if(vm.getId() == null) {
            p = new PresenceKBM();
            p.setPresenceDate(LocalDate.now());
        } else {
            Optional<PresenceKBM> op = this.presenceKBMRepository.findById(vm.getId());
            if(op.isPresent()) {
               p = op.get();
            } else {
                throw new InternalServerErrorException("id absen tidak ditemukan");
            }
        }
        p.setSubjectScheduleTeacher(new SubjectScheduleTeacher(vm.getSubjectScheduleTeacherId()));
        p.setPresenceStatus(new PresenceStatus(vm.getStatusId() == null? id.smartpesantren.constant.PresenceStatus.HADIR: vm.getStatusId()));
        p.setTeacher(new PersonData(vm.getTeacherId()));
        p.setNote(vm.getNote());
        for (Iterator<PresenceKBMStudent> iterator = p.getStudents().iterator(); iterator.hasNext();) {
            PresenceKBMStudent d = iterator.next();
            boolean used = false;
            for(PresenceKbmVMStudent di: vm.getStudents()) {
                if(di.getId() != null && di.getId().equalsIgnoreCase(d.getId())) {
                    used = true;
                    break;
                }
            }
            if(!used) {
                iterator.remove();
            }
        }
        for(PresenceKbmVMStudent d: vm.getStudents()) {
            PresenceKBMStudent cs = null;
            if(p.getId() == null) {
                cs = new PresenceKBMStudent();
            } else {
                if(d.getId() == null) {
                    cs = new PresenceKBMStudent();
                } else {
                    // Check if existing detail needs deletion
                    boolean existingDetailFound = false;
                    for (PresenceKBMStudent existingDetail : p.getStudents()) {
                        if (existingDetail.getId().equals(d.getId())) {
                            cs = existingDetail;
                            existingDetailFound = true;
                            break;
                        }
                    }
                    if(!existingDetailFound) {
                        System.out.println("detail not exists");
                        cs = new PresenceKBMStudent(); // Inisialisasi cs jika detail tidak ditemukan
                    }
                }
            }
            cs.setPresenceKBM(p);
            cs.setId(d.getId());
            cs.setStudent(new Student(d.getStudentId()));
            cs.setNote(d.getNote());
            cs.setAttachment(d.getAttachment());
            cs.setPresenceStatus(new PresenceStatus(d.getPresenceStatusId()));
            if(cs.getId() == null) {
                p.getStudents().add(cs);
            }
        }
        presenceKBMRepository.save(p);
        vm.setId(p.getId());
    }
}

