package id.smartpesantren.web;

import id.smartpesantren.service.MutabaahService;
import id.smartpesantren.web.rest.vm.MutabaahVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pengasuhan/mutabaah")
public class MutabaahResource {
    @Autowired
    private MutabaahService mutabaahService;

    @PostMapping
    private void createMutabaah(@RequestBody MutabaahVM vm) {
        mutabaahService.save(vm);
    }

}
