package id.smartpesantren.web;

import id.smartpesantren.dto.GuardianDTO;
import id.smartpesantren.repository.PersonDataRepository;
import id.smartpesantren.service.GuardianService;
import id.smartpesantren.web.rest.vm.GuardianVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/academic/guardian")
public class GuardianResource {
    @Autowired
    GuardianService service;

    @PostMapping
    public void create(@Valid @RequestBody GuardianVM vm) {
        service.createOrUpdate(vm);
    }

    @GetMapping
    public Page<GuardianDTO> filter(@RequestParam(value = "q") String q, @RequestParam(value = "sex") String sex, Pageable p) {
        q = q==null? "": q;
        return service.filter(
                "%"+q.toUpperCase()+"%",
                sex == null? "": sex,
                p);
    }
}
