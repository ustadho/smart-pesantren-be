package id.smartpesantren.web;

import id.smartpesantren.entity.*;
import id.smartpesantren.dto.ClassRoomDTO;
import id.smartpesantren.repository.ClassRoomRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.BadRequestAlertException;
import id.smartpesantren.web.rest.errors.CodeAlreadyUsedException;
import id.smartpesantren.web.rest.utils.HeaderUtil;
import id.smartpesantren.web.rest.vm.ClassRoomVM;
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
@RequestMapping("/api/academic/class-room")
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
    public Iterable<ClassRoomDTO> findAll(
            @RequestParam(value = "year", required = false, defaultValue = "") String year,
            @RequestParam(value = "ins", required = false, defaultValue = "") String institutionId) {
        return repository.findAll(year, institutionId);
    }

    @GetMapping("/{id}")
    public ClassRoomVM findById(@PathVariable("id") String id) {
        Optional<ClassRoom> ay =  repository.findById(id);
        if(ay.isPresent()) {
            return new ClassRoomVM(ay.get());
        }
        return null;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClassRoomVM> create(@RequestBody @Valid ClassRoomVM vm) throws URISyntaxException {
        log.debug("REST request to save class room : {}", vm);
        Foundation f = new Foundation(SecurityUtils.getFoundationId().get());
        if (vm.getId() != null) {
            throw new BadRequestAlertException("A new class room cannot already have an ID", "classLevel", "idexists");
        } else if (repository.findByFoundationAndAcademicYearAndCode(
                f,
                new AcademicYear(vm.getAcademicYearId()),
                vm.getCode()).isPresent()) {
            throw new CodeAlreadyUsedException();
        } else {
            ClassRoom d = new ClassRoom();
            d.setAcademicYear(new AcademicYear(vm.getAcademicYearId()));
            d.setFoundation(f);
            d.setInstitution(new Institution(vm.getInstitutionId()));
            d.setClassLevel(new ClassLevel(vm.getClassLevelId()));
            d.setCode(vm.getCode());
            d.setName(vm.getName());
            d.setRoom(vm.getRoom());
            d.setCapacity(vm.getCapacity());
            d.setDescription(vm.getDescription());
            d.setHomeRoomTeacher(new PersonData(vm.getHomeTeacherId()));
            d.setLocation(new Location(vm.getLocationId()));
            d.setCurriculum(new Curriculum(vm.getCurriculumId()));

            repository.saveAndFlush(d);
            vm.setId(d.getId());

            return ResponseEntity.created(new URI("/api/siakad/class-room/" + d.getId()))
                    .headers(HeaderUtil.createAlert( "classRoom.created", d.getId()))
                    .body(vm);
        }

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClassRoomVM> update(@PathVariable("id") String id, @RequestBody @Valid ClassRoomVM vm) throws URISyntaxException {
        log.debug("REST request to Update ClassRoomDTO : {}", vm);
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
        Optional<ClassRoom> otherClass = repository.findByFoundationAndAcademicYearAndCode(
                new Foundation(SecurityUtils.getFoundationId().get()),
                new AcademicYear(vm.getAcademicYearId()),
                vm.getCode());
        log.debug("otherClass : {}", otherClass);
        if(otherClass.isPresent() && otherClass.get().getCode().equalsIgnoreCase(vm.getCode()) && !otherClass.get().getId().equalsIgnoreCase(id)) {
           throw new CodeAlreadyUsedException();
        }

        current.setAcademicYear(new AcademicYear(vm.getAcademicYearId()));
        current.setClassLevel(new ClassLevel(vm.getClassLevelId()));
        current.setCode(vm.getCode());
        current.setName(vm.getName());
        current.setRoom(vm.getRoom());
        current.setCapacity(vm.getCapacity());
        current.setDescription(vm.getDescription());
        current.setHomeRoomTeacher(new PersonData(vm.getHomeTeacherId()));
        current.setLocation(new Location(vm.getLocationId()));
        current.setCurriculum(new Curriculum(vm.getCurriculumId()));
        repository.save(current);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert( "classLevel.updated", current.getId()))
                .body(vm);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable("id") String id) throws URISyntaxException {
        repository.deleteById(id);
    }
}
