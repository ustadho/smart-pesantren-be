package id.smartpesantren.service;

import id.smartpesantren.entity.AcademicYear;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.KBMAssesment;
import id.smartpesantren.entity.Student;
import id.smartpesantren.repository.KBMAssesmentRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.service.dto.KBMAssesmentVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KBMAssesmentService {
    @Autowired
    KBMAssesmentRepository kbmAssesmentRepository;

    public KBMAssesmentVM createOrUpdate(KBMAssesmentVM vm) {
        KBMAssesment k = new KBMAssesment();
        k.setId(vm.getId());
        k.setNilaiTugas(vm.getNilaiTugas());
        k.setNilaiUts(vm.getNilaiUts());
        k.setNilaiUas(vm.getNilaiUas());
        k.setNilaiAkhir(vm.getNilaiAkhir());
        k.setSemester(vm.getSemester());
        k.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        k.setAcademicYear(new AcademicYear(vm.getAcademicYearId()));
        k.setStudent(new Student(vm.getStudentId()));
        kbmAssesmentRepository.save(k);

        return vm;
    }
}
