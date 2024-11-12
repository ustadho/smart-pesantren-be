package id.smartpesantren.web;

import id.smartpesantren.dto.SubjectCategoryDTO;
import id.smartpesantren.dto.SubjectDTO;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.Subject;
import id.smartpesantren.entity.SubjectCategory;
import id.smartpesantren.repository.SubjectCategoryRepository;
import id.smartpesantren.repository.SubjectRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.InstitutionVM;
import id.smartpesantren.web.rest.vm.SubjectCategoryVM;
import id.smartpesantren.web.rest.vm.SubjectVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/academic/subject")
public class SubjectResource {
    @Autowired
    SubjectRepository repository;

    @PostMapping
    public void create(@RequestBody @Valid SubjectVM vm) {
        Subject o = new Subject();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(vm.getCode());
        o.setName(vm.getName());
        o.setDescription(vm.getDescription());
        o.setCategory(new SubjectCategory(vm.getCategoryId()));
        o.setActive(vm.getActive());
        repository.save(o);
    }

    @GetMapping
    Page<SubjectDTO> filter(@RequestParam("q") String q, Pageable p) {
        return repository.filter("%"+q.toUpperCase()+"%", p);

    }

    @GetMapping("all")
    Iterable<SubjectDTO> findAll(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return repository.filterAll("%"+q.toUpperCase()+"%");
    }

    @GetMapping("{id}")
    SubjectVM findById(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(SubjectVM::new)
                .orElseThrow(() -> new InternalServerErrorException("Subject could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody SubjectVM req) {
        Optional<Subject> a = repository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("Subject dengan id tersebut tidak ditemukan");
        }
        Subject data = a.get();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setDescription(req.getDescription());
        data.setActive(req.getActive());
        data.setCategory(new SubjectCategory(req.getCategoryId()));
        repository.save(data);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") String id) {
        repository.deleteById(id);
    }
}
