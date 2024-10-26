package id.smartpesantren.web;

import id.smartpesantren.dto.EmployeeCategoryDTO;
import id.smartpesantren.entity.EmployeeCategory;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.repository.EmployeeCategoryRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/hr/employee-category")
public class EmployeeCategoryResource {
    @Autowired
    EmployeeCategoryRepository employeeCategoryRepository;

    @GetMapping("all")
    public Iterable<EmployeeCategoryDTO> filterAll(@RequestParam(value = "q", defaultValue = "") String q) {
        return employeeCategoryRepository.filterAll("%"+q+"%");
    }

    @PostMapping
    public void create(@RequestBody @Valid EmployeeCategoryDTO req) {
        EmployeeCategory o = new EmployeeCategory();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(req.getCode());
        o.setName(req.getName());
        employeeCategoryRepository.save(o);
    }

    @GetMapping
    Page<EmployeeCategoryDTO> filter(@RequestParam("q") String q, Pageable p) {
        return employeeCategoryRepository.filter("%"+q.toUpperCase()+"%", p);

    }

    @GetMapping("{id}")
    EmployeeCategoryDTO findById(@PathVariable("id") String id) {
        return employeeCategoryRepository.findById(id)
                .map(EmployeeCategoryDTO::new)
                .orElseThrow(() -> new InternalServerErrorException("Job Level could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody EmployeeCategoryDTO req) {
        Optional<EmployeeCategory> a = employeeCategoryRepository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("Unit dengan id tersebut tidak ditemukan");
        }
        EmployeeCategory data = a.get();
        data.setCode(req.getCode());
        data.setName(req.getName());
        employeeCategoryRepository.save(data);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") String id) {
        employeeCategoryRepository.deleteById(id);
    }
}
