package id.smartpesantren.service;

import id.smartpesantren.constant.MutabaahType;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.MutabaahRepository;
import id.smartpesantren.repository.StudentRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.MutabaahUjianVM;
import id.smartpesantren.web.rest.vm.MutabaahUjianVMDet;
import id.smartpesantren.web.rest.vm.MutabaahVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MutabaahService {
    @Autowired
    MutabaahRepository repository;

    @Autowired
    StudentRepository studentRepository;

    @Transactional
    public void save(MutabaahVM vm) {
        Mutabaah mutabaah = null;
        if(vm.getId() == null) {
            mutabaah = new Mutabaah();
            mutabaah.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        } else {
            mutabaah = repository.findById(vm.getId()).orElse(new Mutabaah());
        }
        mutabaah.setPembimbing(new PersonData(vm.getPembimbingId()));
        mutabaah.setSantri(new Student(vm.getStudentId()));
        mutabaah.setTanggal(vm.getTanggal());
        mutabaah.setWaktu(new TahfidzTime(vm.getWaktuId()));
        mutabaah.setDari(new TahfidzKonversi(vm.getDariHalamanId()));
        mutabaah.setSampai(new TahfidzKonversi(vm.getSampaiHalamanId()));
        mutabaah.setJumlahHalaman(vm.getJumlahHalaman());
        mutabaah.setTipe(vm.getTipe());
        mutabaah.setNilai(vm.getNilai());
        mutabaah.setCatatan(vm.getCatatan());

        repository.save(mutabaah);
        vm.setId(mutabaah.getId());

        if(vm.getTipe().equalsIgnoreCase(MutabaahType.ZIYADAH)) {
            studentRepository.updateTahfidzCapaian(mutabaah.getSampai().getId(), vm.getStudentId());
        }
    }

    public void saveUjian(MutabaahUjianVM vm) {
        Mutabaah mutabaah = new Mutabaah();
        mutabaah.setPembimbing(new PersonData(vm.getPengujiId()));
        mutabaah.setSantri(new Student(vm.getSantriId()));
        mutabaah.setTanggal(vm.getTanggal());
        mutabaah.setTipe(MutabaahType.UJIAN);
        mutabaah.setNilaiAngka(vm.getNilaiAngka());
        mutabaah.setNilai(vm.getNilai());
        mutabaah.setCatatan(vm.getCatatan());
        mutabaah.setJumlahJuz(vm.getTotalJuz());
        mutabaah.setJumlahHalaman(vm.getTotalHalaman());
        mutabaah.setDari(new TahfidzKonversi(vm.getHalamanAwal()));
        mutabaah.setSampai(new TahfidzKonversi(vm.getHalamanAkhir()));
        mutabaah.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));

        for (MutabaahUjianVMDet detail : vm.getDetails()) {
            MutabaahUjian mutabaahUjian = new MutabaahUjian();
            mutabaahUjian.setId(detail.getId());
            mutabaahUjian.setMutabaah(mutabaah);
            mutabaahUjian.setHalaman(detail.getHalaman());
            mutabaahUjian.setAyat1(detail.getAyat1());
            mutabaahUjian.setCatatan1(detail.getCatatan1());
            mutabaahUjian.setAyat2(detail.getAyat2());
            mutabaahUjian.setCatatan2(detail.getCatatan2());
            mutabaahUjian.setAyat3(detail.getAyat3());
            mutabaahUjian.setCatatan3(detail.getCatatan3());
            mutabaahUjian.setAyat4(detail.getAyat4());
            mutabaahUjian.setCatatan4(detail.getCatatan4());
            mutabaah.getUjians().add(mutabaahUjian);
        }
        repository.save(mutabaah);
    }
}
