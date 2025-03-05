package id.smartpesantren.web;

import id.smartpesantren.entity.IzinCuti;
import id.smartpesantren.entity.PersonData;
import id.smartpesantren.entity.PresenceStatus;
import id.smartpesantren.repository.IzinCutiRepository;
import id.smartpesantren.web.rest.vm.IzinCutiVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/api/izin-cuti")
public class IzinCutiResource {
    @Autowired
    IzinCutiRepository izinCutiRepository;

    @PostMapping
    public void createIzinCuti(@RequestBody @Valid IzinCutiVM vm) {
        IzinCuti i = new IzinCuti();
        i.setCategory(new PresenceStatus(vm.getCategoryId()));
        i.setEmployee(new PersonData(vm.getEmployeeId()));
        i.setStartDate(vm.getStartDate());
        i.setEndDate(vm.getEndDate());
        i.setNote(vm.getNote());

        izinCutiRepository.save(i);
    }
}
