package id.smartpesantren.service;

import id.smartpesantren.entity.*;
import id.smartpesantren.repository.KBMAssesmentRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.service.dto.KBMAssesmentVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KBMAssesmentService {
    @Autowired
    KBMAssesmentRepository kbmAssesmentRepository;

    public KBMAssesmentVM createOrUpdate(KBMAssesmentVM vm) {
        KBMAssesment k = null;
        if(vm.getId() != null) {
            Optional<KBMAssesment> o = kbmAssesmentRepository.findById(vm.getId());
            if(o.isPresent()) {
                k = o.get();
            }
        } else {
            k = new KBMAssesment();
        }
        k.setNilaiTugas(vm.getNilaiTugas());
        k.setNilaiUts(vm.getNilaiUTS());
        k.setNilaiUas(vm.getNilaiUAS());
        k.setNilaiAkhir(vm.getNilaiAkhir());
        k.setSemester(vm.getSemester());
        k.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        k.setClassRoom(new ClassRoom(vm.getClassRoomId()));
        k.setSubject(new Subject(vm.getSubjectId()));
        k.setStudent(new Student(vm.getStudentId()));
        kbmAssesmentRepository.save(k);
        vm.setId(k.getId());
        return vm;
    }
}
