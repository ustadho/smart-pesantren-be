package id.smartpesantren.service;

import id.smartpesantren.dto.PelanggaranDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.PelanggaranRepository;
import id.smartpesantren.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PelanggaranService {
    @Autowired
    PelanggaranRepository repository;

    public PelanggaranDTO save(PelanggaranDTO vm) {
        Pelanggaran p = null;
        if(vm.getId() == null) {
            p = new Pelanggaran();
            p.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        }
        p.setTanggal(vm.getTanggal());
        p.setStudent(new Student(vm.getStudentId()));
        p.setJenisPelanggaran(new JenisPelanggaran(vm.getJenisPelanggaranId()));
        p.setKeterangan(vm.getKeterangan());
        p.setAttachment(vm.getAttachment());
        repository.save(p);

        vm.setId(p.getId());
        return vm;
    }
}
