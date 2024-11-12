package id.smartpesantren.web;

import id.smartpesantren.dto.InstitutionDTO;
import id.smartpesantren.dto.JobLevelDTO;
import id.smartpesantren.entity.EducationLevel;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.Institution;
import id.smartpesantren.entity.JobLevel;
import id.smartpesantren.repository.InstitutionRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.InstitutionVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/institution")
public class InstitutionResource {
    @Autowired
    InstitutionRepository repository;

    @PostMapping
    public void createJobLevel(@RequestBody @Valid InstitutionVM vm) {
        Institution o = new Institution();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(vm.getCode());
        o.setName(vm.getName());
        o.setDescription(vm.getDescription());
        o.setLevel(new EducationLevel(vm.getLevelId()));
        o.setActive(vm.getActive());
        repository.save(o);
    }

    @GetMapping
    Page<InstitutionDTO> filter(@RequestParam("q") String q, Pageable p) {
        return repository.filterJobLevel("%"+q.toUpperCase()+"%", p);

    }

    @GetMapping("all")
    Iterable<InstitutionDTO> findAllOrganization(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return repository.findAllJobLevel("%"+q.toUpperCase()+"%");
    }

    @GetMapping("{id}")
    InstitutionVM findById(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(InstitutionVM::new)
                .orElseThrow(() -> new InternalServerErrorException("Institution could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody InstitutionVM req) {
        Optional<Institution> a = repository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("Institution dengan id tersebut tidak ditemukan");
        }
        Institution data = a.get();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setDescription(req.getDescription());
        data.setLevel(new EducationLevel(req.getLevelId()));
        data.setActive(req.getActive());
        repository.save(data);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") String id) {
        repository.deleteById(id);
    }
}
