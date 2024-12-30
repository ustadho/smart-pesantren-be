package id.smartpesantren.web;

import id.smartpesantren.dto.PesantrenDTO;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.Pesantren;
import id.smartpesantren.repository.PesantrenRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.PesantrenVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/pesantren")
public class PesantrenResource {
    @Autowired
    PesantrenRepository repository;

    @PostMapping
    public void createJobLevel(@RequestBody @Valid PesantrenVM vm) {
        Pesantren o = new Pesantren();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(vm.getCode());
        o.setName(vm.getName());
        o.setDescription(vm.getDescription());
        o.setSex(vm.getSex());
        o.setActive(vm.getActive());
        repository.save(o);
    }

    @GetMapping
    Page<PesantrenDTO> filter(@RequestParam("q") String q, Pageable p) {
        return repository.filterPesantren("%"+q.toUpperCase()+"%", p);

    }

    @GetMapping("all")
    Iterable<PesantrenDTO> findAllOrganization(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return repository.findAllPesantren("%"+q.toUpperCase()+"%");
    }

    @GetMapping("{id}")
    PesantrenVM findById(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(PesantrenVM::new)
                .orElseThrow(() -> new InternalServerErrorException("Pesantren could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody PesantrenVM req) {
        Optional<Pesantren> a = repository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("Pesantren dengan id tersebut tidak ditemukan");
        }
        Pesantren data = a.get();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setDescription(req.getDescription());
        data.setSex(req.getSex());
        data.setActive(req.getActive());
        repository.save(data);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") String id) {
        repository.deleteById(id);
    }
}
