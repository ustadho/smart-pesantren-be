package id.smartpesantren.web;

import id.smartpesantren.dto.WorkingHourDTO;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.WorkingHour;
import id.smartpesantren.repository.WorkingHourRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.service.WorkingHourService;
import id.smartpesantren.web.rest.errors.CodeAlreadyUsedException;
import id.smartpesantren.web.rest.errors.DataNotFoundException;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/hr/working-hour")
public class WorkingHourResource {
    @Autowired
    WorkingHourRepository workingHourRepository;

    @Autowired
    WorkingHourService workingHourService;

    @GetMapping
    public Page<WorkingHourDTO> filter(@RequestParam(value = "q", defaultValue = "") String q, Pageable p) {
        return workingHourRepository.filter("%"+q+"%", p);
    }

    @GetMapping("/{id}")
    public WorkingHourDTO findById(@PathVariable String id) {
        return workingHourRepository.findById(id)
                .map(WorkingHourDTO::new)
                .orElseThrow(() -> new DataNotFoundException("data tidak ditemukan"));
    }

    @GetMapping("all")
    public Iterable<WorkingHourDTO> findAllWorkHour(@RequestParam String q) {
        return workingHourRepository.findAll("%"+q+"%");
    }

    @PostMapping
    public WorkingHourDTO createWorkHour(@RequestBody @Valid WorkingHourDTO req) {
        Optional<WorkingHour> ec = workingHourRepository.findByCode(req.getCode());
        if (ec.isPresent()) {
            throw new CodeAlreadyUsedException();
        }
        return workingHourService.createOrUpdate(req, null);
    }

    @PutMapping("/{id}")
    public WorkingHourDTO updateWorkHour(@PathVariable String id, @RequestBody @Valid WorkingHourDTO req) {
        Optional<WorkingHour> e = workingHourRepository.findById(id);
        if (!e.isPresent()) {
            throw new InternalServerErrorException("Data dengan id tersebut tidak ditemukan");
        }
        if(!e.get().getCode().equalsIgnoreCase(req.getCode())) {
            Optional<WorkingHour> ec = workingHourRepository.findByCode(req.getCode());
            if (ec.isPresent()) {
                throw new CodeAlreadyUsedException();
            }
        }

        return workingHourService.createOrUpdate(req, e.get());
    }

    @DeleteMapping("/{id}")
    public void deleteWorkHour(@PathVariable String id) {
        Optional<WorkingHour> e = workingHourRepository.findById(id);
        if (!e.isPresent()) {
            throw new InternalServerErrorException("Data dengan id tersebut tidak ditemukan");
        }
        workingHourRepository.deleteById(id);
    }
}
