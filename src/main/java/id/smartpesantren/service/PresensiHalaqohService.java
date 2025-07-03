package id.smartpesantren.service;

import id.smartpesantren.entity.*;
import id.smartpesantren.repository.PresensiHalaqohRepository;
import id.smartpesantren.repository.PresensiHalaqohSantriRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Service
public class PresensiHalaqohService {
    @Autowired
    PresensiHalaqohRepository repository;

    @Autowired
    PresensiHalaqohSantriRepository presensiHalaqohSantriRepository;

    private Logger logger = LoggerFactory.getLogger(PresensiHalaqohService.class);

    public PresensiHalaqohVM save(PresensiHalaqohVM vm) {
        PresensiHalaqoh p = null;
        if(vm.getId() == null) {
            p = new PresensiHalaqoh();
            p.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        }
        p.setTanggal(vm.getTanggal());
        p.setHalaqoh(new Halaqoh(vm.getHalaqohId()));
        p.setPembimbing(new PersonData(vm.getPembimbingId()));
        p.setPresenceStatus(new PresenceStatus(vm.getPresenceStatusId()));
        p.setTahfidzTime(new TahfidzTime(vm.getTahfidzTimeId()));
        p.setCatatan(vm.getPresenceNote());
        repository.save(p);
        vm.setPresenceDate(p.getCreatedDate());
        vm.setId(p.getId());
        return vm;
    }

    public PresensiHalaqohVM findByHalaqohIdAndTahfidzTimeId(String halaqohId, String tanggal, Integer tahfidzTimeId) throws ParseException {
        PresensiHalaqohVM vm = null;
        PresensiHalaqohQuery query = repository.findPresenceByHalaqohIdAndTahfidzTimeId(halaqohId, tanggal, tahfidzTimeId);

        if(query != null) {
            vm = new PresensiHalaqohVM();
            vm.setId(query.getPresenceId());
            vm.setTanggal(dateFormat.parse(tanggal));
            vm.setHalaqohId(query.getHalaqohId());
            vm.setHalaqohName(query.getHalaqohName());
            vm.setPembimbingId(query.getPembimbingId());
            vm.setPembimbingName(query.getPembimbingName());
            vm.setTahfidzTimeId(query.getTahfidzTimeId());
            vm.setTahfidzTimeName(query.getTahfidzTimeName());
            vm.setPresenceStatusId(query.getPresenceStatusId());
            vm.setPresenceStatusName(query.getPresenceStatusName());
            vm.setPresenceStatusId(query.getPresenceStatusId());
            vm.setPresenceStatusName(query.getPresenceStatusName());
            vm.setPresenceDate(query.getPresenceDate());
            vm.setPresenceNote(query.getPresenceNote());
            vm.setJumlahSantri(query.getJumlahSantri());
        }
        return vm;
    }

    public PresensiHalaqohSantriVM findHalaqohSantriByPresensiGuru(String presensiHalaqohId) {
        PresensiHalaqohSantriVM vm = new PresensiHalaqohSantriVM();
        vm.setId(presensiHalaqohId);
        Integer jumlahSantriAlpha = 0;
        Integer jumlahSantriSakit = 0;
        Integer jumlahSantriIzin = 0;
        Integer jumlahSantri = 0;
        List<PresensiHalaqohSantriQuery> santris = presensiHalaqohSantriRepository.findSantriByPresensiHalaqohId(presensiHalaqohId);
        logger.debug("santris ==>[{}]", santris.size());
        for(PresensiHalaqohSantriQuery d: santris) {
            PresensiHalaqohSantriVMDet vmd = new PresensiHalaqohSantriVMDet();
            vmd.setSantriId(d.getSantriId());
            vmd.setSantriName(d.getSantriName());
            vmd.setClassRoom(d.getClassRoom());
            vmd.setStatusId(d.getStatusId());
            vmd.setStatusName(d.getStatusName());

            vmd.setId(d.getId());
            vmd.setPhoto(d.getPhoto());
            vmd.setCatatan(d.getCatatan());
            vm.getDetails().add(vmd);

            if(d.getStatusId() != null) {
                if(d.getStatusId() == id.smartpesantren.constant.PresenceStatus.SAKIT ) {
                    jumlahSantriSakit++;
                }
                if(d.getStatusId() == id.smartpesantren.constant.PresenceStatus.IZIN ) {
                    jumlahSantriIzin++;
                }
                if(d.getStatusId() == id.smartpesantren.constant.PresenceStatus.ALPHA ) {
                    jumlahSantriAlpha++;
                }
            }
            jumlahSantri++;
        }
        vm.setJumlahSantri(jumlahSantri);
        vm.setJumlahSantriAlpha(jumlahSantriAlpha);
        vm.setJumlahSantriSakit(jumlahSantriSakit);
        vm.setJumlahSantriIzin(jumlahSantriIzin);
        return vm;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public PresensiHalaqohSantriVM savePresensiHalaqohSantri(PresensiHalaqohSantriVM vm) {
        if(vm.getId() == null || vm.getDetails() == null || vm.getDetails().isEmpty()) {
            return null;
        }
        repository.findById(vm.getId()).ifPresent(p -> {
            List<PresensiHalaqohSantri> details = new ArrayList<>();
            for(PresensiHalaqohSantriVMDet d: vm.getDetails()) {
                PresensiHalaqohSantri ps = new PresensiHalaqohSantri();
                ps.setId(d.getId());
                ps.setPresensiHalaqoh(p);
                ps.setSantri(new Student(d.getSantriId()));
                ps.setStatus(new PresenceStatus(d.getStatusId()));
                ps.setCatatan(d.getCatatan());
                details.add(ps);
            }
            presensiHalaqohSantriRepository.saveAll(details);
        });

        return vm;
    }
}
