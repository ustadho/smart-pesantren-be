package id.smartpesantren.service;

import id.smartpesantren.entity.ClassRoom;
import id.smartpesantren.entity.ClassRoomStudent;
import id.smartpesantren.entity.Student;
import id.smartpesantren.repository.ClassRoomRepository;
import id.smartpesantren.repository.ClassRoomStudentRepository;
import id.smartpesantren.web.rest.vm.ClassRoomStudentVM;
import id.smartpesantren.web.rest.vm.ClassRoomStudentVMDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ClassRoomStudentService {
    @Autowired
    ClassRoomStudentRepository repository;

    @Autowired
    ClassRoomRepository classRoomRepository;

    @Transactional
    public void save(ClassRoomStudentVM vm) {
        ClassRoom cr =  classRoomRepository.findById(vm.getClassRoomId()).get();
        for (Iterator<ClassRoomStudent> iterator = cr.getStudents().iterator(); iterator.hasNext();) {
            ClassRoomStudent d = iterator.next();
            boolean used = false;
            for(ClassRoomStudentVMDetail di: vm.getStudents()) {
                if(di.getId() != null && di.getId().equalsIgnoreCase(d.getId())) {
                    used = true;
                    break;
                }
            }
            if(!used) {
                iterator.remove();
            }
        }
        for(ClassRoomStudentVMDetail d: vm.getStudents()) {
            ClassRoomStudent cs = null;
            if(cr.getId() == null) {
                cs = new ClassRoomStudent();
            } else {
                if(d.getId() == null) {
                    cs = new ClassRoomStudent();
                } else {
                    // Check if existing detail needs deletion
                    boolean existingDetailFound = false;
                    for (ClassRoomStudent existingDetail : cr.getStudents()) {
                        if (existingDetail.getId().equals(d.getId())) {
                            cs = existingDetail;
                            existingDetailFound = true;
                            break;
                        }
                    }
                    if(!existingDetailFound) {
                        System.out.println("detail not exists");
                    }
                }
            }
            cs.setClassRoom(cr);
            cs.setId(d.getId());
            cs.setStudent(new Student(d.getStudentId()));
            if(cs.getId() == null) {
                cr.getStudents().add(cs);
            }
        }

        classRoomRepository.save(cr);
    }

    public ClassRoomStudentVM findById(String id) {
        Optional<ClassRoom> cr = classRoomRepository.findById(id);
        ClassRoomStudentVM vm = new ClassRoomStudentVM();
        vm.setClassRoomId(cr.get().getId());

        for(ClassRoomStudent s: cr.get().getStudents()) {
            ClassRoomStudentVMDetail d = new ClassRoomStudentVMDetail();
            d.setId(s.getId());
            d.setStudentId(s.getStudent().getId());
            d.setStudentName(s.getStudent().getName());
            d.setStudentNisn(s.getStudent().getNisn());
            d.setStudentNis(s.getStudent().getNis());
            d.setJoinYear(s.getStudent().getJoinYear().getCode());
            vm.getStudents().add(d);
        }
//        vm.getStudents().stream().sorted(Comparator.comparing(t -> t.getName()));
        List<ClassRoomStudentVMDetail> students = vm.getStudents();
        students.sort(Comparator.comparing(ClassRoomStudentVMDetail::getStudentName));

        return vm;
    }
}
