package id.smartpesantren.dto;

import id.smartpesantren.repository.EmployeeStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/hr/employee-status")
public class EmployeeStatusResource {
    @Autowired
    EmployeeStatusRepository employeeStatusRepository;

    @GetMapping("all")
    public Iterable<BaseEnityDTO> findAll() {
        return employeeStatusRepository.retrieveAll();
    }
}
