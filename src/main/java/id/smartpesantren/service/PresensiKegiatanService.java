package id.smartpesantren.service;

import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.JenisKegiatan;
import id.smartpesantren.entity.PresensiKegiatan;
import id.smartpesantren.entity.Student;
import id.smartpesantren.repository.PresensiKegiatanRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.PresensiKegiatanVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PresensiKegiatanService {
    @Autowired
    PresensiKegiatanRepository presensiKegiatanRepository;

    public PresensiKegiatanVM save(PresensiKegiatanVM vm) {
        PresensiKegiatan p = null;
        if(vm.getId() == null) {
            p = new PresensiKegiatan();
            p.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        }
        p.setTanggal(vm.getTanggal());
        p.setSantri(new Student(vm.getSantriId()));
        p.setJenisKegiatan(new JenisKegiatan(vm.getJenisKegiatanId()));
        p.setPenilaian(vm.getPenilaian());
        p.setCatatan(vm.getCatatan());
        presensiKegiatanRepository.save(p);

        vm.setId(p.getId());
        return vm;
    }
}
