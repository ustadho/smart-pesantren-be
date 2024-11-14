package id.smartpesantren.web;

import id.smartpesantren.entity.Religion;
import id.smartpesantren.repository.EmploymentTypeRepository;
import id.smartpesantren.repository.ReligionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employment-type")
public class EmploymentTypeResource {
    @Autowired
    EmploymentTypeRepository repository;

    @GetMapping("all")
    public Iterable<EmployementType> findAll() {
        return repository.findAll();
    }
}
