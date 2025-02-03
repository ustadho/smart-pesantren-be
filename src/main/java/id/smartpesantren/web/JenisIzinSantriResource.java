package id.smartpesantren.web;

import id.smartpesantren.dto.JenisIzinSantriDTO;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.JenisIzinSantri;
import id.smartpesantren.repository.JenisIzinSantriRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.JenisIzinSantriVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/pengasuhan/jenis-izin-santri")
public class JenisIzinSantriResource {
    @Autowired
    JenisIzinSantriRepository repository;

    @PostMapping
    public void create(@RequestBody @Valid JenisIzinSantriVM vm) {
        JenisIzinSantri o = new JenisIzinSantri();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(vm.getCode());
        o.setName(vm.getName());
        o.setDescription(vm.getDescription());
        o.setMaxDays(vm.getMaxDays());
        o.setActive(vm.getActive());
        repository.save(o);
    }

    @GetMapping
    Page<JenisIzinSantriDTO> filter(@RequestParam("q") String q, Pageable p) {
        return repository.filter("%"+q.toUpperCase()+"%", p);
    }

    @GetMapping("all")
    Iterable<JenisIzinSantriDTO> findAll(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return repository.filterAll("%"+q.toUpperCase()+"%");
    }

    @GetMapping("{id}")
    JenisIzinSantriVM findById(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(JenisIzinSantriVM::new)
                .orElseThrow(() -> new InternalServerErrorException("Jenis Izin Santri could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody JenisIzinSantriVM req) {
        Optional<JenisIzinSantri> a = repository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("Jenis Izin Santri dengan id tersebut tidak ditemukan");
        }
        JenisIzinSantri data = a.get();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setDescription(req.getDescription());
        data.setActive(req.getActive());
        data.setMaxDays(req.getMaxDays());
        repository.save(data);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") String id) {
        repository.deleteById(id);
    }
}
