package id.smartpesantren.web;

import id.smartpesantren.entity.AcademicYear;
import id.smartpesantren.dto.AcademicYearDTO;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.repository.AcademicYearRepository;
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
    public AcademicYearDTO findById(@PathVariable("id") String id) {
        Optional<AcademicYear> ay =  repository.findById(id);
        if(ay.isPresent()) {
            return new AcademicYearDTO(
                    ay.get().getId(),
                    ay.get().getCode(),
                    ay.get().getName(),
                    ay.get().getDescription(),
                    ay.get().getStartDate(),
                    ay.get().getEndDate(),
                    ay.get().getDefault()
            );
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
            req.setEndDate(req.getEndDate());
            req.setDefault(req.getDefault());

            newData = repository.saveAndFlush(newData);
            req.setId(newData.getId());
            if(req.getDefault()) {
                repository.resetOtherDefault(newData.getId());
            }
            return ResponseEntity.created(new URI("/api/academic/academic-year/" + newData.getId()))
                    .headers(HeaderUtil.createAlert( "academicYear.created", newData.getId()))
                    .body(req);
        }

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AcademicYearDTO> updateAcademicYear(@PathVariable("id") String id, @RequestBody @Valid AcademicYearDTO req) throws URISyntaxException {
        log.debug("REST request to Update Academic year : {}", req);

        if (id == null) {
            throw new BadRequestAlertException("A new academic must be provided", "academicYear", "idNotFound");
        }
        AcademicYear current = repository.findById(id).get();
        if (current == null) {
            throw new BadRequestAlertException("Academic Year data not found", "academicYear", "notFound");
        }
        if(req.getCode().equalsIgnoreCase(current.getCode())) { //ada perubahan kode
            AcademicYear otherCode = repository.findByFoundationAndCode(new Foundation(SecurityUtils.getFoundationId().get()), req.getCode()).get();
            if(otherCode !=null && otherCode.getCode().equalsIgnoreCase(req.getCode()) && !otherCode.getId().equalsIgnoreCase(id)) {
                throw new BadRequestAlertException("Code alrady used", "academicYear", "alreadyExist");
            }
        }
        req.setId(current.getId());
        current.setCode(req.getCode());
        current.setDescription(req.getDescription());
        current.setName(req.getName());
        current.setStartDate(req.getStartDate());
        current.setEndDate(req.getEndDate());
        current.setDefault(req.getDefault());
        repository.save(current);
        if(req.getDefault()) {
            repository.resetOtherDefault(current.getId());
        }
        return ResponseEntity.ok()
                    .headers(HeaderUtil.createAlert( "academicYear.updated", current.getId()))
                    .body(req);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteAcademicYear(@PathVariable("id") String id) throws URISyntaxException {
        repository.deleteById(id);
    }
}
