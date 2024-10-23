package id.smartpesantren.web;

import id.smartpesantren.dto.SectionDTO;
import id.smartpesantren.entity.Section;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.repository.SectionRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/hr/section")
public class SectionResource {
    @Autowired
    SectionRepository sectionRepository;

    @PostMapping
    public void create(@RequestBody @Valid SectionDTO req) {
        Section o = new Section();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(req.getCode());
        o.setName(req.getName());
        o.setDescription(req.getDescription());
        sectionRepository.save(o);
    }

    @GetMapping
    Page<SectionDTO> filter(@RequestParam("q") String q, Pageable p) {
        return sectionRepository.filter("%"+q.toUpperCase()+"%", p);

    }

    @GetMapping("all")
    Iterable<SectionDTO> findAllOrganization(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return sectionRepository.retrieveAll("%"+q.toUpperCase()+"%");
    }

    @GetMapping("{id}")
    SectionDTO findById(@PathVariable("id") String id) {
        return sectionRepository.findById(id)
                .map(SectionDTO::new)
                .orElseThrow(() -> new InternalServerErrorException("Job Level could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody SectionDTO req) {
        Optional<Section> a = sectionRepository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("Unit dengan id tersebut tidak ditemukan");
        }
        Section data = a.get();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setDescription(req.getDescription());
        sectionRepository.save(data);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") String id) {
        sectionRepository.deleteById(id);
    }
}
