package id.smartpesantren.service;

import id.smartpesantren.dto.PrestasiDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.PrestasiRepository;
import id.smartpesantren.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestasiService {
    @Autowired
    PrestasiRepository repository;

    public PrestasiDTO save(PrestasiDTO vm) {
        Prestasi p = null;
        if(vm.getId() == null) {
            p = new Prestasi();
            p.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        }
        p.setTanggal(vm.getTanggal());
        p.setStudent(new Student(vm.getStudentId()));
        p.setJenisPrestasi(new JenisPrestasi(vm.getJenisPrestasiId()));
        p.setKeterangan(vm.getKeterangan());
        p.setAttachment(vm.getAttachment());
        repository.save(p);

        vm.setId(p.getId());
        return vm;
    }
}
