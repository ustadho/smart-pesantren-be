package id.smartpesantren.web;

import id.smartpesantren.service.PresensiAsramaService;
import id.smartpesantren.web.rest.vm.PresensiAsramaVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pengasuhan/presensi-asrama")
public class PresensiAsramaResource {
    @Autowired
    PresensiAsramaService service;

    @PostMapping
    public PresensiAsramaVM createPresensiAsrama(@RequestBody PresensiAsramaVM vm) {
        return service.createOrUpdate(vm);
    }


}
