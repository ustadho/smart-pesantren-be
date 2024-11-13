package id.smartpesantren.web;

import id.smartpesantren.entity.AbstractAuditingEntity;
import id.smartpesantren.dto.ClassLevelDTO;
import id.smartpesantren.entity.ClassLevel;
import id.smartpesantren.entity.EducationLevel;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.repository.ClassLevelRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.BadRequestAlertException;
import id.smartpesantren.web.rest.errors.CodeAlreadyUsedException;
import id.smartpesantren.web.rest.utils.HeaderUtil;
import id.smartpesantren.web.rest.vm.ClassLevelVM;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/academic/class-level")
public class ClassLevelResource {
    private final Logger log = LoggerFactory.getLogger(ClassLevelResource.class);

    @Autowired
    ClassLevelRepository repository;

    @GetMapping("")
    public Page<ClassLevelDTO> filter(@RequestParam("q") String q, Pageable pageable) {
        return repository.filter(
                "%"+q.toUpperCase()+"%",
                pageable
        );
    }

    @GetMapping("all")
    public Iterable<ClassLevelDTO> findAll(@RequestParam(value = "el", required = false, defaultValue = "") Integer educationLevelId) {
        return repository.findAllClass(educationLevelId);
    }

    @GetMapping("/{id}")
    public ClassLevelDTO findById(@PathVariable("id") String id) {
        Optional<ClassLevel> ay =  repository.findById(id);
        if(ay.isPresent()) {
            return new ClassLevelDTO(ay.get());
        }
        return null;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClassLevelVM> createClassLevel(@RequestBody @Valid ClassLevelVM vm) throws URISyntaxException {
        log.debug("REST request to save class level : {}", vm);

        if (vm.getId() != null) {
            throw new BadRequestAlertException("A new class level cannot already have an ID", "classLevel", "idexists");
        } else if (repository.findByFoundationAndLevel(new Foundation(SecurityUtils.getFoundationId().get()), vm.getLevel()).isPresent()) {
            throw new CodeAlreadyUsedException();
        } else {
            ClassLevel d = new ClassLevel().fromVM(vm);
            repository.saveAndFlush(d);
            vm.setId(d.getId());

            return ResponseEntity.created(new URI("/api/academic/class-level/" + d.getId()))
                    .headers(HeaderUtil.createAlert( "classLevel.created", d.getId()))
                    .body(vm);
        }

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClassLevelVM> updateClassLevel(@PathVariable("id") String id, @RequestBody @Valid ClassLevelVM req) throws URISyntaxException {
        log.debug("REST request to Update ClassLevelDTO : {}", req);

        if (id == null) {
            throw new BadRequestAlertException("A new academic must be provided", "academicYear", "idNotFound");
        }
        ClassLevel current = repository.findById(id).get();
        if (current == null) {
            throw new BadRequestAlertException("ClassLevel data not found", "classLevel", "notFound");
        }
        if(req.getLevel() == current.getLevel()) { //ada perubahan kode
            ClassLevel otherLevel = repository.findByFoundationAndLevel(new Foundation(SecurityUtils.getFoundationId().get()), req.getLevel()).get();
            if(otherLevel !=null && otherLevel.getLevel() == req.getLevel() && !otherLevel.getId().equalsIgnoreCase(id)) {
                throw new BadRequestAlertException("Level alrady used", "classLevel", "alreadyExist");
            }
        }
        req.setId(current.getId());
        current.setLevel(req.getLevel());
        current.setDescription(req.getDescription());
        current.setEducationLevel(new EducationLevel(req.getEducationLevelId()));
        current.setCode(req.getCode());
        current.setName(req.getName());
        repository.save(current);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert( "classLevel.updated", current.getId()))
                .body(req);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteClassLevel(@PathVariable("id") String id) throws URISyntaxException {
        repository.deleteById(id);
    }
}
