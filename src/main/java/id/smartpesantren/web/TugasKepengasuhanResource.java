package id.smartpesantren.web;

import id.smartpesantren.dto.TugasKepengasuhanDTO;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.TugasKepengasuhan;
import id.smartpesantren.repository.TugasKepengasuhanRepository;
import id.smartpesantren.repository.TugasKepengasuhanRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.TugasKepengasuhanVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/pengasuhan/tugas-kepengasuhan")
public class TugasKepengasuhanResource {
    @Autowired
    TugasKepengasuhanRepository repository;

    @PostMapping
    public void create(@RequestBody @Valid TugasKepengasuhanVM vm) {
        TugasKepengasuhan o = new TugasKepengasuhan();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(vm.getCode());
        o.setName(vm.getName());
        o.setDescription(vm.getDescription());
        o.setActive(vm.getActive());
        repository.save(o);
    }

    @GetMapping
    Page<TugasKepengasuhanDTO> filter(@RequestParam("q") String q, Pageable p) {
        return repository.filter("%"+q.toUpperCase()+"%", p);
    }

    @GetMapping("all")
    Iterable<TugasKepengasuhanDTO> findAll(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return repository.filterAll("%"+q.toUpperCase()+"%");
    }

    @GetMapping("{id}")
    TugasKepengasuhanVM findById(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(TugasKepengasuhanVM::new)
                .orElseThrow(() -> new InternalServerErrorException("Tugas Kepengasuhan could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody TugasKepengasuhanVM req) {
        Optional<TugasKepengasuhan> a = repository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("Tugas Kepengasuhan dengan id tersebut tidak ditemukan");
        }
        TugasKepengasuhan data = a.get();
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
