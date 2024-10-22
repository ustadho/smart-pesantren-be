package id.smartpesantren.web;

import id.smartpesantren.dto.JobPositionDTO;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.JobLevel;
import id.smartpesantren.entity.JobPosition;
import id.smartpesantren.repository.JobPositionRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.CodeAlreadyUsedException;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/hr/job-position")
public class JobPositionResource {
    @Autowired
    JobPositionRepository jobPositionRepository;

    Logger logger = LoggerFactory.getLogger(JobPositionResource.class);

    @PostMapping
    public void createOrganization(@RequestBody @Valid JobPositionDTO req) {
        // cek kode apakah sudah pernah dipakai
        Optional<JobPosition> exist = jobPositionRepository.findByCode(req.getCode());
        if(exist.isPresent()) {
            throw new CodeAlreadyUsedException();
        }
        JobPosition o = new JobPosition();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(req.getCode());
        o.setName(req.getName());
        o.setDescription(req.getDescription());
        o.setJobLevel(new JobLevel(req.getJobLevelId()));
        o.setActive(req.getActive());
        jobPositionRepository.save(o);
    }

    @GetMapping
    Page<JobPositionDTO> filter(@RequestParam("q") String q, Pageable p) {
        return jobPositionRepository.filterJobPosition("%"+q.toUpperCase()+"%", p);

    }

    @GetMapping("all")
    Iterable<JobPositionDTO> findAllOrganization(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return jobPositionRepository.findAllJobPosition("%"+q.toUpperCase()+"%");
    }

    @GetMapping("{id}")
    JobPositionDTO findById(@PathVariable("id") String id) {
        return jobPositionRepository.findById(id)
                .map(JobPositionDTO::new)
                .orElseThrow(() -> new InternalServerErrorException("Job Position could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody JobPositionDTO req) {
        Optional<JobPosition> eid = jobPositionRepository.findById(id);
        if (!eid.isPresent()) {
            throw new InternalServerErrorException("Jabatan dengan id tersebut tidak ditemukan");
        }
        // cek barangkali kode baru berbeda
        if(!req.getCode().equalsIgnoreCase(eid.get().getCode())) {
            logger.info("ada perubahan kode");
            Optional<JobPosition> ecode = jobPositionRepository.findByCode(req.getCode());
            if (ecode.isPresent() && !eid.get().getId().equalsIgnoreCase(ecode.get().getId())) {
                throw new CodeAlreadyUsedException();
            }
        }


        JobPosition data = eid.get();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setDescription(req.getDescription());
        data.setActive(req.getActive());
        jobPositionRepository.save(data);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") String id) {
        jobPositionRepository.deleteById(id);
    }
}
