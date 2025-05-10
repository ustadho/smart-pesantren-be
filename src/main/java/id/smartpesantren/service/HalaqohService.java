package id.smartpesantren.service;

import id.smartpesantren.dto.HalaqohDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.AsramaMappingRepository;
import id.smartpesantren.repository.HalaqohRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.AsramaMappingVM;
import id.smartpesantren.web.rest.vm.AsramaMappingVMStudent;
import id.smartpesantren.web.rest.vm.HalaqohVM;
import id.smartpesantren.web.rest.vm.PesantrenVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class HalaqohService {
    @Autowired
    HalaqohRepository repository;

    @Transactional
    public void save(HalaqohVM vm) {
        Halaqoh a = null;
        if(vm.getId() != null) {
            a = repository.findById(vm.getId()).get();
        } else {
            a = new Halaqoh();
        }
        a.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        a.setAcademicYear(new AcademicYear(vm.getAcademicYearId()));
        a.setPesantren(new Pesantren(vm.getPesantrenId()));
        a.setDescription(vm.getDescription());
        // Ubah agar support manyToMany
        if(vm.getMusyrifIds() != null) {
            a.setMusyrifs(vm.getMusyrifIds().stream().map(PersonData::new).collect(java.util.stream.Collectors.toList()));
        } else {
            a.setMusyrifs(null);
        }

        for (Iterator<HalaqohStudent> iterator = a.getStudents().iterator(); iterator.hasNext();) {
            HalaqohStudent d = iterator.next();
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
            HalaqohStudent d = null;
            if(a.getId() == null) {
                d = new HalaqohStudent();
            } else {
                if(vmd.getId() == null) {
                    d = new HalaqohStudent();
                } else {
                    // Check if existing detail needs deletion
                    boolean existingDetailFound = false;
                    for (HalaqohStudent existingDetail : a.getStudents()) {
                        if (vmd.getId() != null &&  existingDetail.getId().equals(vmd.getId())) {
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
            d.setId(d.getId());
            d.setStudent(new Student(vmd.getStudentId()));
            d.setHalaqoh(a);
            if(d.getId() == null) {
                a.getStudents().add(d);
            }
        }

        repository.save(a);
    }

    public HalaqohVM findByPesantrenAndYearId(String pesantrenId, String academicYearId) {
        Halaqoh a = repository.findTop1ByPesantrenAndAcademicYear(new Pesantren(pesantrenId), new AcademicYear(academicYearId));
        if(a == null) {
            return null;
        }
        return toVM(a);
    }

    public HalaqohVM findById(String id) {
        Optional<Halaqoh> cr = repository.findByHalaqohId(id);
        return toVM(cr.get());
    }

    public HalaqohVM    toVM(Halaqoh am) {
        HalaqohVM vm = new HalaqohVM();
        vm.setId(am.getId());
        vm.setPesantrenId(am.getPesantren().getId());
        vm.setPesantrenName(am.getPesantren().getName());
        vm.setDescription(am.getDescription());
        vm.setAcademicYearId(am.getAcademicYear().getId());
        vm.setAcademicYearName(am.getAcademicYear().getName());
        vm.setMusyrifIds(
            am.getMusyrifs() == null ? null :
            am.getMusyrifs().stream().map(PersonData::getId).collect(java.util.stream.Collectors.toList())
        );

        for(HalaqohStudent s: am.getStudents()) {
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
