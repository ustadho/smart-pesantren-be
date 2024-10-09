package id.smartpesantren.web;

import id.smartpesantren.entity.AcademicYear;
import id.smartpesantren.entity.ClassLevel;
import id.smartpesantren.entity.ClassRoom;
import id.smartpesantren.repository.AuthorityRepository;
import id.smartpesantren.entity.AbstractAuditingEntity;
import id.smartpesantren.dto.ClassRoomDTO;
import id.smartpesantren.repository.ClassRoomRepository;
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
@RequestMapping("/api/siakad/class-room")
public class ClassRoomResource {
    private final Logger log = LoggerFactory.getLogger(ClassRoomResource.class);

    @Autowired
    ClassRoomRepository repository;

    @GetMapping("")
    public Page<ClassRoomDTO> filter(@RequestParam("year") String year, @RequestParam("q") String q, Pageable pageable) {
        return repository.filter(
                year,
                "%"+q.toUpperCase()+"%",
                pageable
        );
    }

    @GetMapping("all")
    public Iterable<ClassRoomDTO> findAll(@RequestParam("year") String year) {
        return repository.findAll(year);
    }

    @GetMapping("/{id}")
    public ClassRoomDTO findById(@PathVariable("id") String id) {
        Optional<ClassRoom> ay =  repository.findById(id);
        if(ay.isPresent()) {
            return new ClassRoomDTO(
                    ay.get().getId(),
                    ay.get().getAcademicYear().getId(),
                    ay.get().getAcademicYear().getCode(),
                    ay.get().getLevel().getId(),
                    ay.get().getLevel().getLevel(),
                    ay.get().getCode(),
                    ay.get().getName(),
                    ay.get().getCapacity(),
                    ay.get().getDescription()
            );
        }
        return null;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClassRoomDTO> create(@RequestBody @Valid ClassRoomDTO req) throws URISyntaxException {
        log.debug("REST request to save class room : {}", req);

        if (req.getId() != null) {
            throw new BadRequestAlertException("A new class room cannot already have an ID", "classLevel", "idexists");
        } else if (repository.findByAcademicYearAndCode(
                new AcademicYear(req.getAcademicYearId()),
                req.getCode()).isPresent()) {
            throw new CodeAlreadyUsedException();
        } else {
            ClassRoom newData = repository.saveAndFlush(new ClassRoom(
                    null,
                    new AcademicYear(req.getAcademicYearId()),
                    new ClassLevel(req.getLevelId()),
                    req.getCode(),
                    req.getName(),
                    req.getCapacity(),
                    req.getDescription()
            ));
            req.setId(newData.getId());

            return ResponseEntity.created(new URI("/api/siakad/class-room/" + newData.getId()))
                    .headers(HeaderUtil.createAlert( "classRoom.created", newData.getId()))
                    .body(req);
        }

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClassRoomDTO> update(@PathVariable("id") String id, @RequestBody @Valid ClassRoomDTO req) throws URISyntaxException {
        log.debug("REST request to Update ClassRoomDTO : {}", req);
        log.debug("PUT id : {}", id);

        if (id == null) {
            throw new BadRequestAlertException("A new class room ID must be provided", "classRoom", "idNotFound");
        }
        ClassRoom current = repository.findById(id).get();
        log.debug("current : {}", current);
        if (current == null) {
            System.out.println("current-tidak-ketemu");
            throw new BadRequestAlertException("ClassRoom data not found", "classRoom", "notFound");
        }
        Optional<ClassRoom> otherClass = repository.findByAcademicYearAndCode(
                new AcademicYear(req.getAcademicYearId()),
                req.getCode());
        log.debug("otherClass : {}", otherClass);
        if(otherClass.isPresent() && otherClass.get().getCode().equalsIgnoreCase(req.getCode()) && !otherClass.get().getId().equalsIgnoreCase(id)) {
           throw new CodeAlreadyUsedException();
        }

        req.setId(current.getId());
        current.setAcademicYear(new AcademicYear(req.getAcademicYearId()));
        current.setLevel(new ClassLevel(req.getLevelId()));
        current.setCode(req.getCode());
        current.setName(req.getName());
        current.setDescription(req.getDescription());
        current.setCapacity(req.getCapacity());
        repository.save(current);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert( "classLevel.updated", current.getId()))
                .body(req);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable("id") String id) throws URISyntaxException {
        repository.deleteById(id);
    }
}
