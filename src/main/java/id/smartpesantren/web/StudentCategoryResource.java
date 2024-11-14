package id.smartpesantren.web;

import id.smartpesantren.dto.ClassLevelDTO;
import id.smartpesantren.entity.ClassLevel;
import id.smartpesantren.entity.EducationLevel;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.StudentCategory;
import id.smartpesantren.repository.StudentCategoryRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.BadRequestAlertException;
import id.smartpesantren.web.rest.errors.CodeAlreadyUsedException;
import id.smartpesantren.web.rest.utils.HeaderUtil;
import id.smartpesantren.web.rest.vm.ClassLevelVM;
import id.smartpesantren.web.rest.vm.StudentCategoryVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/academic/student-category")
public class StudentCategoryResource {
    @Autowired
    StudentCategoryRepository repository;

    private final Logger log = LoggerFactory.getLogger(StudentCategoryResource.class);

    @GetMapping
    public Page<StudentCategoryVM> filter(@RequestParam(value = "q", defaultValue = "") String q, Pageable p) {
        return repository.filter("%"+q.toUpperCase()+"%", p);
    }

    @GetMapping("all")
    public List<StudentCategoryVM> findAll() {
        return repository.findAllTypes();
    }

    @GetMapping("/{id}")
    public StudentCategoryVM findById(@PathVariable("id") String id) {
        Optional<StudentCategory> a =  repository.findById(id);
        if(a.isPresent()) {
            return new StudentCategoryVM(a.get());
        }
        return null;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<StudentCategoryVM> create(@RequestBody @Valid StudentCategoryVM vm) throws URISyntaxException {
        log.debug("REST request to save student category : {}", vm);

        if (vm.getId() != null) {
            throw new BadRequestAlertException("A new student category level cannot already have an ID", "student category", "idexists");
        } else {
            StudentCategory d = new StudentCategory().fromVM(vm);
            repository.saveAndFlush(d);
            vm.setId(d.getId());
            if(vm.getDefault() == true) {
                repository.updateDefault(d.getId());
            }
            return ResponseEntity.created(new URI("/api/academic/student-category/" + d.getId()))
                    .headers(HeaderUtil.createAlert( "studentCategory.created", d.getId()))
                    .body(vm);
        }

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<StudentCategoryVM> update(@PathVariable("id") String id, @RequestBody @Valid StudentCategoryVM vm) throws URISyntaxException {
        log.debug("REST request to Update StudentCategoryVM : {}", vm);

        if (id == null) {
            throw new BadRequestAlertException("A new academic must be provided", "academicYear", "idNotFound");
        }
        StudentCategory current = repository.findById(id).get();
        if (current == null) {
            throw new BadRequestAlertException("StudentCategory data not found", "studentCategory", "notFound");
        }
        if(!vm.getName().equalsIgnoreCase(current.getName())) { //ada perubahan nama
            StudentCategory other = repository.findByFoundationAndName(new Foundation(SecurityUtils.getFoundationId().get()), vm.getName()).get();
            if(other !=null && !other.getId().equalsIgnoreCase(id)) {
                throw new BadRequestAlertException("student category alrady used", "student category", "alreadyExist");
            }
        }
        vm.setId(current.getId());
        current.setDescription(vm.getDescription());
        current.setName(vm.getName());
        current.setDefault(vm.getDefault());
        repository.save(current);
        if(vm.getDefault() == true) {
            repository.updateDefault(vm.getId());
        }
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert( "student category.updated", current.getId()))
                .body(vm);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable("id") String id) throws URISyntaxException {
        repository.deleteById(id);
    }
}
