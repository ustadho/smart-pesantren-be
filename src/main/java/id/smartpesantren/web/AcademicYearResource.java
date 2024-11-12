package id.smartpesantren.web;

import id.smartpesantren.entity.AcademicYear;
import id.smartpesantren.dto.AcademicYearDTO;
import id.smartpesantren.entity.Curriculum;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.repository.AcademicYearRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.BadRequestAlertException;
import id.smartpesantren.web.rest.errors.CodeAlreadyUsedException;
import id.smartpesantren.web.rest.utils.HeaderUtil;
import id.smartpesantren.web.rest.vm.AcademicYearVM;
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
@RequestMapping("/api/academic-year")
public class AcademicYearResource {
    private final Logger log = LoggerFactory.getLogger(AcademicYearResource.class);

    @Autowired
    AcademicYearRepository repository;

    @GetMapping("")
    public Page<AcademicYearDTO> filter(@RequestParam("q") String q, Pageable pageable) {
        return repository.filter(
                    "%"+q.toUpperCase()+"%",
                    pageable
                );
    }

    @GetMapping("all")
    public Iterable<AcademicYearDTO> findAll() {
        return repository.findAllData();
    }

    @GetMapping("/{id}")
    public AcademicYearVM findById(@PathVariable("id") String id) {
        Optional<AcademicYear> ay =  repository.findById(id);
        if(ay.isPresent()) {
            return new AcademicYearVM(ay.get());
        }
        return null;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AcademicYearDTO> createAcademicYear(@RequestBody @Valid AcademicYearDTO req) throws URISyntaxException {
        log.debug("REST request to save Academic year : {}", req);

        if (req.getId() != null) {
            throw new BadRequestAlertException("A new academic year cannot already have an ID", "academicYear", "idexists");
            // Lowercase the user login before comparing with database
        } else if (repository.findByFoundationAndCode(new Foundation(SecurityUtils.getFoundationId().get()), req.getCode()).isPresent()) {
            throw new CodeAlreadyUsedException();
        } else {
            AcademicYear newData = new AcademicYear();
            newData.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
            newData.setCode(req.getCode());
            newData.setName(req.getName());
            newData.setDescription(req.getDescription());
            newData.setStartDate(req.getStartDate());
            newData.setEndDate(req.getEndDate());
            newData.setDefault(req.getIsDefault());
            newData.setCurriculum(req.getCurriculum()==null? null: new Curriculum(req.getCurriculum()));

            newData = repository.saveAndFlush(newData);
            req.setId(newData.getId());
            if(req.getIsDefault()) {
                repository.resetOtherDefault(newData.getId());
            }
            return ResponseEntity.created(new URI("/api/academic/academic-year/" + newData.getId()))
                    .headers(HeaderUtil.createAlert( "academicYear.created", newData.getId()))
                    .body(req);
        }

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AcademicYearVM> updateAcademicYear(@PathVariable("id") String id, @RequestBody @Valid AcademicYearVM vm) throws URISyntaxException {
        log.debug("REST request to Update Academic year : {}", vm);

        if (id == null) {
            throw new BadRequestAlertException("A new academic must be provided", "academicYear", "idNotFound");
        }
        AcademicYear current = repository.findById(id).get();
        if (current == null) {
            throw new BadRequestAlertException("Academic Year data not found", "academicYear", "notFound");
        }
        if(vm.getCode().equalsIgnoreCase(current.getCode())) { //ada perubahan kode
            AcademicYear otherCode = repository.findByFoundationAndCode(new Foundation(SecurityUtils.getFoundationId().get()), vm.getCode()).get();
            if(otherCode !=null && otherCode.getCode().equalsIgnoreCase(vm.getCode()) && !otherCode.getId().equalsIgnoreCase(id)) {
                throw new BadRequestAlertException("Code alrady used", "academicYear", "alreadyExist");
            }
        }
        vm.setId(current.getId());
        current.setCode(vm.getCode());
        current.setDescription(vm.getDescription());
        current.setName(vm.getName());
        current.setStartDate(vm.getStartDate());
        current.setEndDate(vm.getEndDate());
        current.setDefault(vm.getIsDefault());
        current.setCurriculum(vm.getCurriculumId()==null? null: new Curriculum(vm.getCurriculumId()));
        repository.save(current);
        if(vm.getIsDefault()) {
            repository.resetOtherDefault(current.getId());
        }
        return ResponseEntity.ok()
                    .headers(HeaderUtil.createAlert( "academicYear.updated", current.getId()))
                    .body(vm);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteAcademicYear(@PathVariable("id") String id) throws URISyntaxException {
        repository.deleteById(id);
    }
}
