package id.smartpesantren.web;

import id.smartpesantren.dto.ReferalInstitutionDTO;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.ReferalInstitution;
import id.smartpesantren.repository.ReferalInstitutionRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.BadRequestAlertException;
import id.smartpesantren.web.rest.errors.CodeAlreadyUsedException;
import id.smartpesantren.web.rest.errors.DataNotFoundException;
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

@RestController
@RequestMapping("/api/hr/referal-institution")
public class ReferalInstitutionResource {
    private final Logger log = LoggerFactory.getLogger(ReferalInstitutionResource.class);

    @Autowired
    ReferalInstitutionRepository repository;

    @GetMapping("")
    public Page<ReferalInstitutionDTO> filter(@RequestParam("q") String q, Pageable pageable) {
        return repository.filter(
                "%"+q.toUpperCase()+"%",
                pageable
        );
    }

    @GetMapping("all")
    public Iterable<ReferalInstitutionDTO> findAll(@RequestParam("q") String q) {
        return repository.findAll("%"+q+"%");
    }

    @GetMapping("/{id}")
    public ReferalInstitutionDTO findById(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(ReferalInstitutionDTO::new)
                .orElseThrow(DataNotFoundException::new);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<ReferalInstitutionDTO> create(@RequestBody @Valid ReferalInstitutionDTO req) throws URISyntaxException {
        log.debug("REST request to save referal institution : {}", req);

        if (req.getId() != null) {
            throw new BadRequestAlertException("A new Referal Institution cannot already have an ID", "referalInstitution", "idexists");
        } else if (repository.findByName(req.getName()).isPresent()) {
            throw new CodeAlreadyUsedException();
        } else {
            ReferalInstitution newData = repository.saveAndFlush(
                    new ReferalInstitution(
                        null,
                        new Foundation(SecurityUtils.getFoundationId().get()),
                        req.getName(),
                        req.getDescription()
                    )
            );
            req.setId(newData.getId());

            return ResponseEntity.created(new URI("/api/hr/referal-institution/" + newData.getId()))
                    .headers(HeaderUtil.createAlert( "referalInstitution.created", newData.getId()))
                    .body(req);
        }

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ReferalInstitutionDTO> update(@PathVariable("id") String id, @RequestBody @Valid ReferalInstitutionDTO req) throws URISyntaxException {
        log.debug("REST request to Update ReferalInstitutionDTO : {}", req);

        if (id == null) {
            throw new BadRequestAlertException("A new academic must be provided", "academicYear", "idNotFound");
        }
        ReferalInstitution current = repository.findById(id).get();
        if (current == null) {
            throw new BadRequestAlertException("ReferalInstitution data not found", "referalInstitution", "notFound");
        }
        req.setId(current.getId());
        current.setName(req.getName());
        current.setDescription(req.getDescription());
        repository.save(current);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert( "referalInstitution.updated", current.getId()))
                .body(req);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable("id") String id) throws URISyntaxException {
        repository.deleteById(id);
    }
}
