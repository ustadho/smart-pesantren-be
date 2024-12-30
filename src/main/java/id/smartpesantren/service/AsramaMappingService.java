package id.smartpesantren.service;

import id.smartpesantren.entity.*;
import id.smartpesantren.repository.AsramaMappingRepository;
import id.smartpesantren.repository.AsramaRepository;
import id.smartpesantren.repository.ClassRoomRepository;
import id.smartpesantren.repository.ClassRoomStudentRepository;
import id.smartpesantren.web.rest.vm.AsramaMappingVM;
import id.smartpesantren.web.rest.vm.AsramaMappingVMStudent;
import id.smartpesantren.web.rest.vm.ClassRoomStudentVM;
import id.smartpesantren.web.rest.vm.ClassRoomStudentVMDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AsramaMappingService {
    @Autowired
    AsramaMappingRepository repository;

    @Transactional
    public void save(AsramaMappingVM vm) {
        AsramaMapping a =  repository.findById(vm.getId()).get();
        for (Iterator<AsramaMappingStudent> iterator = a.getStudents().iterator(); iterator.hasNext();) {
            AsramaMappingStudent d = iterator.next();
            boolean used = false;
            for(AsramaMappingVMStudent di: vm.getStudents()) {
                if(di.getId() != null && di.getId().equalsIgnoreCase(d.getId())) {
                    used = true;
                    break;
                }
            }
            if(!used) {
                iterator.remove();
            }
        }
        for(AsramaMappingVMStudent vmd: vm.getStudents()) {
            AsramaMappingStudent d = null;
            if(a.getId() == null) {
                d = new AsramaMappingStudent();
            } else {
                if(d.getId() == null) {
                    d = new AsramaMappingStudent();
                } else {
                    // Check if existing detail needs deletion
                    boolean existingDetailFound = false;
                    for (AsramaMappingStudent existingDetail : a.getStudents()) {
                        if (existingDetail.getId().equals(d.getId())) {
                            d = existingDetail;
                            existingDetailFound = true;
                            break;
                        }
                    }
                    if(!existingDetailFound) {
                        System.out.println("detail not exists");
                    }
                }
            }
            d.setAsramaMapping(a);
            d.setId(d.getId());
            d.setStudent(new Student(vmd.getStudentId()));
            if(d.getId() == null) {
                a.getStudents().add(d);
            }
        }

        repository.save(a);
    }

    public AsramaMappingVM findById(String id) {
        Optional<AsramaMapping> cr = repository.findById(id);
        AsramaMappingVM vm = new AsramaMappingVM();
        vm.setAsramaId(cr.get().getId());

        for(AsramaMappingStudent s: cr.get().getStudents()) {
            AsramaMappingVMStudent d = new AsramaMappingVMStudent();
            d.setId(s.getId());
            d.setStudentId(s.getStudent().getId());
            d.setStudentName(s.getStudent().getName());
            d.setStudentNisn(s.getStudent().getNisn());
            d.setStudentNis(s.getStudent().getNis());
            d.setJoinYear(s.getStudent().getJoinYear().getCode());
            vm.getStudents().add(d);
        }
//        vm.getStudents().stream().sorted(Comparator.comparing(t -> t.getName()));
        List<AsramaMappingVMStudent> students = vm.getStudents();
        students.sort(Comparator.comparing(AsramaMappingVMStudent::getStudentName));

        return vm;
    }
}
