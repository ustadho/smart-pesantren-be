package id.smartpesantren.service;

import id.smartpesantren.entity.*;
import id.smartpesantren.repository.PresensiHalaqohRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.service.dto.PresensiHalaqohQuery;
import id.smartpesantren.service.dto.PresensiHalaqohVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class PresensiHalaqohService {
    @Autowired
    PresensiHalaqohRepository repository;

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
        }
        return vm;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
}
