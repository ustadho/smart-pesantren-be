package id.smartpesantren.web;

import id.smartpesantren.dto.EmployeeTransferDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.DataNotFoundException;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.EmployeeTransferVM;
import id.smartpesantren.repository.EmployeeTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/hr/employee-transfer")
public class EmployeeTransferResource {
    @Autowired
    EmployeeTransferRepository employeeTransferRepository;;

    @PostMapping
    public EmployeeTransferVM save(@RequestBody @Valid EmployeeTransferVM vm) {
        Optional<EmployeeTransfer> exist = employeeTransferRepository.findByEmployeeAndEffectiveDate(new PersonData(vm.getEmployeeId()), vm.getEffectiveDate());
        if(exist.isPresent()) {
            throw new InternalServerErrorException("data tersebut sudah pernah dimasukkan");
        }
        EmployeeTransfer et = new EmployeeTransfer();
        et.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        et.setEmployee(new PersonData(vm.getEmployeeId()));
        et.setType(new HRTransferType(vm.getTypeId()));
        et.setStatus(new EmployeeStatus(vm.getStatusId()));
        et.setOrganization(new Organization(vm.getOrganizationId()));
        et.setSection(new Section(vm.getSectionId()));
        et.setPosition(new JobPosition(vm.getPositionId()));
        et.setEffectiveDate(vm.getEffectiveDate());
        et.setManager(vm.getManagerId() == null? null: new PersonData(vm.getManagerId()));
        et.setDescription(vm.getDescription());
        employeeTransferRepository.save(et);
        vm.setId(et.getId());
        return vm;
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") String id, @RequestBody @Valid EmployeeTransferVM vm) {
        Optional<EmployeeTransfer> exist = employeeTransferRepository.findById(id);
        if(!exist.isPresent()) {
            throw new DataNotFoundException("Employee transfer with id [{'"+id+"'}] not found!");
        }

        EmployeeTransfer et = exist.get();
        et.setEmployee(new PersonData(vm.getEmployeeId()));
        et.setType(new HRTransferType(vm.getTypeId()));
        et.setStatus(new EmployeeStatus(vm.getStatusId()));
        et.setOrganization(new Organization(vm.getOrganizationId()));
        et.setSection(new Section(vm.getSectionId()));
        et.setPosition(new JobPosition(vm.getPositionId()));
        et.setEffectiveDate(vm.getEffectiveDate());
        et.setManager(vm.getManagerId() == null? null: new PersonData(vm.getManagerId()));
        et.setDescription(vm.getDescription());
        employeeTransferRepository.save(et);
        vm.setId(et.getId());
    }

    @GetMapping
    public Page<EmployeeTransferDTO> filter(@RequestParam(value = "q") String q, Pageable p) {
        return employeeTransferRepository.filter("%"+q.toUpperCase()+"%", p);
    }

    @GetMapping("/{id}")
    public EmployeeTransferVM findById(@PathVariable("id") String id) {
        return employeeTransferRepository.findById(id)
                .map(EmployeeTransferVM::new)
                .orElseThrow(()-> new DataNotFoundException("EmployeeTransfer tidak ditemukan"));
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        employeeTransferRepository.deleteById(id);
    }
}
