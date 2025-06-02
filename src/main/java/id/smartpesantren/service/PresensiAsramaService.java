package id.smartpesantren.service;

import id.smartpesantren.entity.*;
import id.smartpesantren.repository.PresensiAsramaRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.PresensiAsramaVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PresensiAsramaService {
    @Autowired
    PresensiAsramaRepository presensiAsramaRepository;

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
        s.setCatatan(vm.getCatatan());
        s.setPresenceStatus(new PresenceStatus(vm.getPresenceStatusId()));
        return vm;
    }
}
