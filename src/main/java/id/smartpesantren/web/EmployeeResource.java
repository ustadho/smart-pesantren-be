package id.smartpesantren.web;

import id.smartpesantren.dto.EmployeeDTO;
import id.smartpesantren.entity.PersonData;
import id.smartpesantren.service.EmployeeService;
import id.smartpesantren.web.rest.errors.DataNotFoundException;
import id.smartpesantren.web.rest.vm.EmployeeVM;
import id.smartpesantren.repository.PersonDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hr/employee")
public class EmployeeResource {
    @Autowired
    PersonDataRepository personDataRepository;

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    private EmployeeVM create(@RequestBody @Valid EmployeeVM vm) {
        return employeeService.createOrUpdate(vm, null);
    }


    @PutMapping("/{id}")
    private EmployeeVM update(@PathVariable("id") String id, @RequestBody @Valid EmployeeVM vm) {
        Optional<PersonData> exist = personDataRepository.findById(id);
        if(!exist.isPresent()) {
            throw new DataNotFoundException("data tidak ditemukan!");
        }
        vm.setId(id);
        return employeeService.createOrUpdate(vm, exist.get());
    }

    @GetMapping
    private Page<EmployeeDTO> filter(
            @RequestParam(value = "q", required = false, defaultValue = "") String q,
            @RequestParam(value = "unor", required = false, defaultValue = "") String unor,
            Pageable p) {
        return personDataRepository.filterEmployee(unor,"%"+q.toUpperCase()+ "%", p);
    }

    @GetMapping("all")
    private List<EmployeeDTO> filterAll(
            @RequestParam(value = "q", required = false, defaultValue = "") String q,
            @RequestParam(value = "limit", defaultValue = "1000") Integer limit) {
        Sort sort = Sort.by("name").ascending();
        PageRequest pageRequest = PageRequest.of(0, limit, sort);

        return personDataRepository.filterEmployee("", "%"+q.toUpperCase()+ "%", pageRequest).getContent();
    }

    @GetMapping("/{id}")
    private EmployeeVM findById(@PathVariable("id") String id) {
        return personDataRepository
                .findById(id)
                .map(EmployeeVM::new)
                .orElseThrow(() -> new DataNotFoundException("data tidak ditemukan"));
    }
}