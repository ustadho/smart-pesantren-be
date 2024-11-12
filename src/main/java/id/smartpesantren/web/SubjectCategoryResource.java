package id.smartpesantren.web;

import id.smartpesantren.dto.SubjectCategoryDTO;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.SubjectCategory;
import id.smartpesantren.repository.SubjectCategoryRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.InstitutionVM;
import id.smartpesantren.web.rest.vm.SubjectCategoryVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/academic/subject-category")
public class SubjectCategoryResource {
    @Autowired
    SubjectCategoryRepository repository;

    @PostMapping
    public void create(@RequestBody @Valid SubjectCategoryVM vm) {
        SubjectCategory o = new SubjectCategory();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setName(vm.getName());
        o.setDescription(vm.getDescription());
        o.setActive(vm.getActive());
        repository.save(o);
    }

    @GetMapping
    Page<SubjectCategoryDTO> filter(@RequestParam("q") String q, Pageable p) {
        return repository.filter("%"+q.toUpperCase()+"%", p);

    }

    @GetMapping("all")
    Iterable<SubjectCategoryDTO> findAll(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return repository.filterAll("%"+q.toUpperCase()+"%");
    }

    @GetMapping("{id}")
    SubjectCategoryVM findById(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(SubjectCategoryVM::new)
                .orElseThrow(() -> new InternalServerErrorException("Subject category could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody InstitutionVM req) {
        Optional<SubjectCategory> a = repository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("SubjectCategory dengan id tersebut tidak ditemukan");
        }
        SubjectCategory data = a.get();
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
