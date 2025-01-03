package id.smartpesantren.web;

import id.smartpesantren.dto.JenisPelanggaranDTO;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.JenisPelanggaran;
import id.smartpesantren.repository.JenisPelanggaranRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.JenisPelanggaranVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/pengasuhan/jenis-pelanggaran")
public class JenisPelanggaranResource {
    @Autowired
    JenisPelanggaranRepository repository;

    @PostMapping
    public void create(@RequestBody @Valid JenisPelanggaranVM vm) {
        JenisPelanggaran o = new JenisPelanggaran();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(vm.getCode());
        o.setName(vm.getName());
        o.setDescription(vm.getDescription());
        o.setActive(vm.getActive());
        repository.save(o);
    }

    @GetMapping
    Page<JenisPelanggaranDTO> filter(@RequestParam("q") String q, Pageable p) {
        return repository.filter("%"+q.toUpperCase()+"%", p);
    }

    @GetMapping("all")
    Iterable<JenisPelanggaranDTO> findAll(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return repository.filterAll("%"+q.toUpperCase()+"%");
    }

    @GetMapping("{id}")
    JenisPelanggaranVM findById(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(JenisPelanggaranVM::new)
                .orElseThrow(() -> new InternalServerErrorException("Jenis Pelanggaran could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody JenisPelanggaranVM req) {
        Optional<JenisPelanggaran> a = repository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("Jenis Pelanggaran dengan id tersebut tidak ditemukan");
        }
        JenisPelanggaran data = a.get();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setDescription(req.getDescription());
        data.setActive(req.getActive());
        repository.save(data);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") String id) {
        repository.deleteById(id);
    }
}
