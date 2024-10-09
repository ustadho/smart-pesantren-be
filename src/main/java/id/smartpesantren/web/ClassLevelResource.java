package id.smartpesantren.web;

import id.smartpesantren.entity.AbstractAuditingEntity;
import id.smartpesantren.dto.ClassLevelDTO;
import id.smartpesantren.entity.ClassLevel;
import id.smartpesantren.repository.ClassLevelRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.BadRequestAlertException;
import id.smartpesantren.web.rest.errors.CodeAlreadyUsedException;
import id.smartpesantren.web.rest.utils.HeaderUtil;
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
@RequestMapping("/api/siakad/class-level")
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
    public Iterable<ClassLevelDTO> findAll() {
        return repository.findAllClass();
    }

    @GetMapping("/{id}")
    public ClassLevelDTO findById(@PathVariable("id") String id) {
        Optional<ClassLevel> ay =  repository.findById(id);
        if(ay.isPresent()) {
            return new ClassLevelDTO(
                    ay.get().getId(),
                    ay.get().getLevel(),
                    ay.get().getDescription()
            );
        }
        return null;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClassLevelDTO> createClassLevel(@RequestBody @Valid ClassLevelDTO req) throws URISyntaxException {
        log.debug("REST request to save class level : {}", req);

        if (req.getId() != null) {
            throw new BadRequestAlertException("A new class level cannot already have an ID", "classLevel", "idexists");
        } else if (repository.findByLevel(req.getLevel()).isPresent()) {
            throw new CodeAlreadyUsedException();
        } else {
            ClassLevel newData = repository.saveAndFlush(new ClassLevel(
                    null,
                    req.getLevel(),
                    req.getDescription()
            ));
            req.setId(newData.getId());

            return ResponseEntity.created(new URI("/api/siakad/class-level/" + newData.getId()))
                    .headers(HeaderUtil.createAlert( "classLevel.created", newData.getId()))
                    .body(req);
        }

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClassLevelDTO> updateClassLevel(@PathVariable("id") String id, @RequestBody @Valid ClassLevelDTO req) throws URISyntaxException {
        log.debug("REST request to Update ClassLevelDTO : {}", req);

        if (id == null) {
            throw new BadRequestAlertException("A new academic must be provided", "academicYear", "idNotFound");
        }
        ClassLevel current = repository.findById(id).get();
        if (current == null) {
            throw new BadRequestAlertException("ClassLevel data not found", "classLevel", "notFound");
        }
        if(req.getLevel() == current.getLevel()) { //ada perubahan kode
            ClassLevel otherLevel = repository.findByLevel(req.getLevel()).get();
            if(otherLevel !=null && otherLevel.getLevel() == req.getLevel() && !otherLevel.getId().equalsIgnoreCase(id)) {
                throw new BadRequestAlertException("Level alrady used", "classLevel", "alreadyExist");
            }
        }
        req.setId(current.getId());
        current.setLevel(req.getLevel());
        current.setDescription(req.getDescription());
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
