package id.smartpesantren.web;

import id.smartpesantren.dto.CurriculumDTO;
import id.smartpesantren.entity.Curriculum;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.repository.CurriculumRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/academic/curriculum")
public class CurriculumResource {
    @Autowired
    CurriculumRepository repository;

    @PostMapping
    public void create(@RequestBody @Valid CurriculumDTO req) {
        Curriculum o = new Curriculum();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(req.getCode());
        o.setName(req.getName());
        o.setStartYear(req.getStartYear());
        o.setDescription(req.getDescription());
        o.setActive(req.getActive());
        repository.save(o);
    }

    @GetMapping
    Page<CurriculumDTO> filter(@RequestParam("q") String q, Pageable p) {
        return repository.filter("%"+q.toUpperCase()+"%", p);

    }

    @GetMapping("all")
    Iterable<CurriculumDTO> findAll(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return repository.findAll("%"+q.toUpperCase()+"%");
    }

    @GetMapping("{id}")
    CurriculumDTO findById(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(CurriculumDTO::new)
                .orElseThrow(() -> new InternalServerErrorException("Curriculum could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody CurriculumDTO req) {
        Optional<Curriculum> a = repository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("Curriculum dengan id tersebut tidak ditemukan");
        }
        Curriculum data = a.get();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setDescription(req.getDescription());
        data.setStartYear(req.getStartYear());
        data.setActive(req.getActive());
        repository.save(data);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") String id) {
        repository.deleteById(id);
    }
}
