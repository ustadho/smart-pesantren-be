package id.smartpesantren.web;

import id.smartpesantren.service.KBMAssesmentService;
import id.smartpesantren.service.dto.KBMAssesmentVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/kbm-assesment")
public class KBMAssesmentResource {
    @Autowired
    KBMAssesmentService service;

    @PostMapping
    public KBMAssesmentVM createKBMAssesment(@RequestBody @Valid KBMAssesmentVM vm) {
        return service.createOrUpdate(vm);
    }

    @PutMapping("/{id}")
    public KBMAssesmentVM updateKBMAssesment(@PathVariable("id") String id, @RequestBody @Valid KBMAssesmentVM vm) {
        return service.createOrUpdate(vm);
    }
}
