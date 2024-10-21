package id.smartpesantren.web;

import id.smartpesantren.dto.JobLevelDTO;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.JobLevel;
import id.smartpesantren.repository.JobLevelRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/hr/job-level")
public class JobLevelResource {
    @Autowired
    JobLevelRepository jobLevelRepository;

    @PostMapping
    public void createJobLevel(@RequestBody @Valid JobLevelDTO req) {
        JobLevel o = new JobLevel();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(req.getCode());
        o.setName(req.getName());
        o.setDescription(req.getDescription());
        o.setLevel(req.getLevel());
        o.setColor(req.getColor());
        jobLevelRepository.save(o);
    }

    @GetMapping
    Page<JobLevelDTO> filter(@RequestParam("q") String q, Pageable p) {
        return jobLevelRepository.filterJobLevel("%"+q.toUpperCase()+"%", p);

    }

    @GetMapping("all")
    Iterable<JobLevelDTO> findAllOrganization(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return jobLevelRepository.findAllJobLevel("%"+q.toUpperCase()+"%");
    }

    @GetMapping("{id}")
    JobLevelDTO findById(@PathVariable("id") String id) {
        return jobLevelRepository.findById(id)
                .map(JobLevelDTO::new)
                .orElseThrow(() -> new InternalServerErrorException("Job Level could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody JobLevelDTO req) {
        Optional<JobLevel> a = jobLevelRepository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("Unit dengan id tersebut tidak ditemukan");
        }
        JobLevel data = a.get();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setDescription(req.getDescription());
        data.setLevel(req.getLevel());
        data.setColor(req.getColor());
        jobLevelRepository.save(data);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") String id) {
        jobLevelRepository.deleteById(id);
    }
}
