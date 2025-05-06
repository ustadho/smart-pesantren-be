package id.smartpesantren.service;

import id.smartpesantren.entity.*;
import id.smartpesantren.repository.AsramaMappingRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.*;
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
        AsramaMapping a = null;
        if(vm.getId() != null) {
            a = repository.findById(vm.getId()).get();
        } else {
            a = new AsramaMapping();
        }
        a.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        a.setAcademicYear(new AcademicYear(vm.getAcademicYearId()));
        a.setAsrama(new Asrama(vm.getAsramaId()));
        // Ubah agar support manyToMany
        if(vm.getMusyrifIds() != null) {
            a.setMusyrifs(vm.getMusyrifIds().stream().map(PersonData::new).collect(java.util.stream.Collectors.toList()));
        } else if(vm.getMusyrifId() != null) {
            a.setMusyrifs(java.util.Collections.singletonList(new PersonData(vm.getMusyrifId())));
        } else {
            a.setMusyrifs(null);
        }

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
                if(vmd.getId() == null) {
                    d = new AsramaMappingStudent();
                } else {
                    // Check if existing detail needs deletion
                    boolean existingDetailFound = false;
                    for (AsramaMappingStudent existingDetail : a.getStudents()) {
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
            d.setAsramaMapping(a);
            d.setId(d.getId());
            d.setStudent(new Student(vmd.getStudentId()));
            if(d.getId() == null) {
                a.getStudents().add(d);
            }
        }

        repository.save(a);
    }

    public AsramaMappingVM findByAsramaAndYearId(String asramaId, String academicYearId) {
        AsramaMapping cr = repository.findTop1ByAsramaAndAcademicYear(new Asrama(asramaId), new AcademicYear(academicYearId));
        if(cr == null) {
            return null;
        }
        return toVM(cr);
    }

    public AsramaMappingVM findById(String id) {
        Optional<AsramaMapping> cr = repository.findById(id);
        return toVM(cr.get());
    }

    public AsramaMappingVM toVM(AsramaMapping am) {
        AsramaMappingVM vm = new AsramaMappingVM();
        vm.setId(am.getId());
        vm.setAsramaId(am.getAsrama().getId());
        vm.setAsramaName(am.getAsrama().getName());
        vm.setDescription(am.getDescription());
        vm.setAcademicYearId(am.getAcademicYear().getId());
        vm.setAcademicYearName(am.getAcademicYear().getName());
        vm.setMusyrifIds(
            am.getMusyrifs() == null ? null :
            am.getMusyrifs().stream().map(PersonData::getId).collect(java.util.stream.Collectors.toList())
        );

        for(AsramaMappingStudent s: am.getStudents()) {
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
