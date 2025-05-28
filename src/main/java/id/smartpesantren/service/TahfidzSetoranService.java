package id.smartpesantren.service;

import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.Student;
import id.smartpesantren.entity.TahfidzSetoran;
import id.smartpesantren.repository.TahfidzSetoranRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.service.dto.TahfidzSetoranVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TahfidzSetoranService {
    @Autowired
    TahfidzSetoranRepository repository;

    public TahfidzSetoranVM createOrUpdate(TahfidzSetoranVM vm) {
        TahfidzSetoran s = null;
        Optional<TahfidzSetoran> find1 = repository.findByStudentAndTanggalAndWaktu(new Student(vm.getStudentId()), vm.getTanggal(), vm.getWaktu());
        if(find1.isPresent()) {
            s = find1.get();
        } else {
            if (vm.getId() != null && !vm.getId().equalsIgnoreCase("")) {
                Optional<TahfidzSetoran> f = repository.findById(vm.getId());
                if (f.isPresent()) {
                    s = f.get();
                }
            } else {
                s = new TahfidzSetoran();
            }
        }
        s.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        s.setStudent(new Student(vm.getStudentId()));
        s.setTanggal(vm.getTanggal());
        s.setWaktu(vm.getWaktu());
        s.setTargetMulai(vm.getTargetMulai());
        s.setTargetSampai(vm.getTargetSampai());
        s.setQiraah(vm.getQiraah());
        s.setHifdz(vm.getHifdz());
        s.setTikrar(vm.getTikrar());
        s.setSetorUlangDari(vm.getSetorUlangDari());
        s.setSetorUlangSampai(vm.getSetorUlangSampai());
        s.setMurajaah(vm.getMurajaah());
        s.setNilai(vm.getNilai());

        repository.save(s);
        vm.setId(s.getId());
        return vm;
    }
}
