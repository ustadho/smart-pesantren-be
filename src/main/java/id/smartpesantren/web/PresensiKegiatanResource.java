package id.smartpesantren.web;

import id.smartpesantren.repository.PresensiKegiatanRepository;
import id.smartpesantren.service.PresensiKegiatanService;
import id.smartpesantren.web.rest.vm.PresensiKegiatanVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/pengasuhan/presensi-kegiatan")
public class PresensiKegiatanResource {

    @Autowired
    PresensiKegiatanService service;

    @Autowired
    PresensiKegiatanRepository repository;

    @GetMapping
    public Page<PresensiKegiatanVM> filter(Pageable p) {
        return repository.filter(p);
    }

    @PostMapping
    public PresensiKegiatanVM create(@Valid @RequestBody PresensiKegiatanVM vm) {
        return service.save(vm);
    }

    @PutMapping
    public PresensiKegiatanVM update(@Valid @RequestBody PresensiKegiatanVM vm) {
        return service.save(vm);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }



}
