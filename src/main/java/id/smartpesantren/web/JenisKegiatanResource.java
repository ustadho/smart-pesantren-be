package id.smartpesantren.web;

import id.smartpesantren.dto.JenisKegiatanDTO;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.JenisKegiatan;
import id.smartpesantren.repository.JenisKegiatanRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.JenisKegiatanVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/pengasuhan/jenis-kegiatan")
public class JenisKegiatanResource {
    @Autowired
    JenisKegiatanRepository repository;

    @PostMapping
    public void create(@RequestBody @Valid JenisKegiatanVM vm) {
        JenisKegiatan o = new JenisKegiatan();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(vm.getCode());
        o.setName(vm.getName());
        o.setDescription(vm.getDescription());
        o.setInTime(vm.getInTime());
        o.setActive(vm.getActive());
        repository.save(o);
    }

    @GetMapping
    Page<JenisKegiatanDTO> filter(@RequestParam("q") String q, Pageable p) {
        return repository.filter("%"+q.toUpperCase()+"%", p);
    }

    @GetMapping("all")
    List<JenisKegiatanDTO> findAll(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return repository.filterAll("%"+q.toUpperCase()+"%");
    }

    @GetMapping("{id}")
    JenisKegiatanVM findById(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(JenisKegiatanVM::new)
                .orElseThrow(() -> new InternalServerErrorException("Jenis Kegiatan could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody JenisKegiatanVM req) {
        Optional<JenisKegiatan> a = repository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("Jenis Kegiatan dengan id tersebut tidak ditemukan");
        }
        JenisKegiatan data = a.get();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setDescription(req.getDescription());
        data.setActive(req.getActive());
        data.setInTime(req.getInTime());
        repository.save(data);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") String id) {
        repository.deleteById(id);
    }
}
