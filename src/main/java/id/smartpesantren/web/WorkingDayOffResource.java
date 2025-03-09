package id.smartpesantren.web;

import id.smartpesantren.dto.EmployeeDayOffDTO;
import id.smartpesantren.repository.WorkingDayOffRepository;
import id.smartpesantren.service.WorkingDayOffService;
import id.smartpesantren.web.rest.vm.WorkingDayOffVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/hr/working-dayoff")
public class WorkingDayOffResource {
    @Autowired
    WorkingDayOffRepository workingDayOffRepository;

    @Autowired
    WorkingDayOffService workingDayOffService;


    @GetMapping("/all")
    Iterable<EmployeeDayOffDTO> findAllDayOff(
            @RequestParam(value = "category", required = false, defaultValue = "") String category,
            @RequestParam(value = "organization", required = false, defaultValue = "") String organization,
            @RequestParam(value = "jobPosition", required = false, defaultValue = "") String jobPosition,
            @RequestParam(value = "q", required = false, defaultValue = "") String q
            ) {
        return workingDayOffRepository.findAllDayOff(category, organization, jobPosition, "%"+q+"%");
    }

    @PutMapping
    public EmployeeDayOffDTO saveOrUpdate(@RequestBody @Valid WorkingDayOffVM vm) {
        return workingDayOffService.saveOrUpdate(vm);
    }
}
