package id.smartpesantren.web;

import id.smartpesantren.dto.WorkingTimeDTO;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.WorkingTime;
import id.smartpesantren.repository.WorkingTimeRepository;
import id.smartpesantren.security.SecurityUtils;
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
@RequestMapping("api/hr/working-time")
public class WorkingTimeResource {
    @Autowired
    WorkingTimeRepository workingTimeRepository;

    @GetMapping
    public Page<WorkingTimeDTO> filter(@RequestParam(value = "q", defaultValue = "") String q, Pageable p) {
        return workingTimeRepository.filter("%"+q+"%", p);
    }

    @GetMapping("/{id}")
    public WorkingTimeDTO findById(@PathVariable String id) {
        return workingTimeRepository.findById(id)
                .map(WorkingTimeDTO::new)
                .orElseThrow(() -> new DataNotFoundException("data tidak ditemukan"));
    }

    @GetMapping("all")
    public Iterable<WorkingTimeDTO> findAllWorkHour(@RequestParam String q) {
        return workingTimeRepository.findAll("%"+q+"%");
    }

    @PostMapping
    public WorkingTimeDTO createWorkHour(@RequestBody @Valid WorkingTimeDTO req) {
        Optional<WorkingTime> e = workingTimeRepository.findByCode(req.getCode());
        if (e.isPresent()) {
            throw new CodeAlreadyUsedException();
        }
        WorkingTime a = new WorkingTime();
        a.setCode(req.getCode());
        a.setName(req.getName());
        a.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        a.setCheckInTime(req.getCheckInTime());
        a.setCheckOutTime(req.getCheckOutTime());
        a.setPreviousDay(req.getPreviousDay());
        a.setNextDate(req.getNextDate());
        a.setLateTolerance(req.getLateTolerance());
        a.setEarlyLeaveTolerance(req.getEarlyLeaveTolerance());
        a.setScanStartCheckInTime(req.getScanStartCheckInTime());
        a.setScanEndCheckInTime(req.getScanEndCheckInTime());
        a.setScanStartCheckOutTime(req.getScanStartCheckOutTime());
        a.setScanEndCheckOutTime(req.getScanEndCheckOutTime());
        a.setColor(req.getColor());
        a.setEarlyLeaveTolerance(req.getEarlyLeaveTolerance());
        a.setNoOfWorkingDays(req.getNoOfWorkingDays());
        workingTimeRepository.save(a);
        req.setId(a.getId());
        return req;
    }

    @PutMapping("/{id}")
    public WorkingTimeDTO updateWorkHour(@PathVariable String id, @RequestBody @Valid WorkingTimeDTO req) {
        WorkingTime a = null;
        Optional<WorkingTime> e = workingTimeRepository.findById(id);
        if (!e.isPresent()) {
            throw new InternalServerErrorException("Data dengan id tersebut tidak ditemukan");
        }
        a = e.get();
        if(!e.get().getCode().equalsIgnoreCase(req.getCode())) {
            Optional<WorkingTime> ec = workingTimeRepository.findByCode(req.getCode());
            if (ec.isPresent()) {
                throw new CodeAlreadyUsedException();
            }
        }

        a.setCode(req.getCode());
        a.setName(req.getName());
        a.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        a.setCheckInTime(req.getCheckInTime());
        a.setCheckOutTime(req.getCheckOutTime());
        a.setPreviousDay(req.getPreviousDay());
        a.setNextDate(req.getNextDate());
        a.setLateTolerance(req.getLateTolerance());
        a.setEarlyLeaveTolerance(req.getEarlyLeaveTolerance());
        a.setScanStartCheckInTime(req.getScanStartCheckInTime());
        a.setScanEndCheckInTime(req.getScanEndCheckInTime());
        a.setScanStartCheckOutTime(req.getScanStartCheckOutTime());
        a.setScanEndCheckOutTime(req.getScanEndCheckOutTime());
        a.setColor(req.getColor());
        a.setEarlyLeaveTolerance(req.getEarlyLeaveTolerance());
        a.setNoOfWorkingDays(req.getNoOfWorkingDays());
        workingTimeRepository.save(a);
        req.setId(a.getId());
        return req;
    }

    @DeleteMapping("/{id}")
    public void deleteWorkHour(@PathVariable String id) {
        Optional<WorkingTime> e = workingTimeRepository.findById(id);
        if (!e.isPresent()) {
            throw new InternalServerErrorException("Data dengan id tersebut tidak ditemukan");
        }
        workingTimeRepository.deleteById(id);
    }
}
