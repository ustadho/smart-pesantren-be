package id.smartpesantren.service;

import id.smartpesantren.dto.PresenceSubjectStudentDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.PresensiAsramaRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.PresensiAsramaVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PresensiAsramaService {
    @Autowired
    PresensiAsramaRepository presensiAsramaRepository;

    @Transactional
    public PresensiAsramaVM createOrUpdate(PresensiAsramaVM vm) {
        PresensiAsrama s = null;
        Optional<PresensiAsrama> find1 = presensiAsramaRepository.findBySantriAndTanggalAndPresenceType(new Student(vm.getSantriId()), vm.getTanggal(), vm.getPresenceType());
        if(find1.isPresent()) {
            s = find1.get();
        } else {
            if (vm.getId() != null && !vm.getId().equalsIgnoreCase("")) {
                Optional<PresensiAsrama> f = presensiAsramaRepository.findById(vm.getId());
                if (f.isPresent()) {
                    s = f.get();
                }
            } else {
                s = new PresensiAsrama();
            }
        }
        s.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        s.setAsrama(new Asrama(vm.getAsramaId()));
        s.setTanggal(vm.getTanggal());
        s.setPresenceType(vm.getPresenceType());
        s.setSantri(new Student(vm.getSantriId()));
        s.setNote(vm.getCatatan());
        s.setPresenceStatus(new PresenceStatus(vm.getPresenceStatusId()));
        return vm;
    }

    @Transactional
    public List<PresenceSubjectStudentDTO> createOrUpdateMultiple(String asramaId, List<PresenceSubjectStudentDTO> vm) {
        for(PresenceSubjectStudentDTO v : vm) {
            PresensiAsrama s = null;
            Optional<PresensiAsrama> find1 = presensiAsramaRepository.findBySantriAndTanggalAndPresenceType(new Student(v.getStudentId()), new Date(), "PR");
            if(find1.isPresent()) {
                s = find1.get();
            } else {
                if (v.getPresenceId() != null && !v.getPresenceId().equalsIgnoreCase("")) {
                    Optional<PresensiAsrama> f = presensiAsramaRepository.findById(v.getPresenceId());
                    if (f.isPresent()) {
                        s = f.get();
                    }
                } else {
                    s = new PresensiAsrama();
                    s.setTanggal(new Date());
                }
            }
            s.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
            s.setAsrama(new Asrama(asramaId));
            s.setPresenceType("PR");
            s.setSantri(new Student(v.getStudentId()));
            s.setNote(v.getNote());
            s.setPresenceStatus(new PresenceStatus(v.getPresenceStatusId()));
        }
        return null;
    }
}
