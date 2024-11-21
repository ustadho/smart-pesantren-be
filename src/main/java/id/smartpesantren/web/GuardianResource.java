package id.smartpesantren.web;

import id.smartpesantren.dto.GuardianDTO;
import id.smartpesantren.entity.PersonData;
import id.smartpesantren.repository.PersonDataRepository;
import id.smartpesantren.service.GuardianService;
import id.smartpesantren.web.rest.errors.DataNotFoundException;
import id.smartpesantren.web.rest.vm.GuardianVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/academic/guardian")
public class GuardianResource {
    @Autowired
    GuardianService service;

    @Autowired
    PersonDataRepository repository;

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

    @GetMapping("/{id}")
    public GuardianVM findById(@PathVariable("id") String id) {
        Optional<PersonData> p = repository.findById(id);
        if(p.isPresent()) {
            return service.fromPersonData(p.get());
        }
        return null;
    }

    @PutMapping("/{id}")
    public GuardianVM update(@PathVariable("id") String id, @RequestBody @Valid GuardianVM vm) {
        Optional<PersonData> p = repository.findById(id);
        if(!p.isPresent()) {
            throw new DataNotFoundException("data tidak ditemukan");
        }
        service.createOrUpdate(vm);
        return null;
    }

    @DeleteMapping("/{id}")
    public GuardianVM update(@PathVariable("id") String id) {
        Optional<PersonData> p = repository.findById(id);
        if(!p.isPresent()) {
            throw new DataNotFoundException("data tidak ditemukan");
        }
        repository.deleteById(id);
        return null;
    }
}
