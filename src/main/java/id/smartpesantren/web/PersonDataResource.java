package id.smartpesantren.web;

import id.smartpesantren.constant.PersonType;
import id.smartpesantren.dto.EmployeeDTO;
import id.smartpesantren.entity.PersonData;
import id.smartpesantren.repository.PersonDataRepository;
import id.smartpesantren.web.rest.errors.DataNotFoundException;
import id.smartpesantren.web.rest.vm.EmployeeVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person-data")
public class PersonDataResource {
    @Autowired
    PersonDataRepository personDataRepository;

    @GetMapping("all")
    private List<EmployeeDTO> filterAll(
            @RequestParam(value = "type", required = false, defaultValue = "") String personType,
            @RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return personDataRepository.findAllByType(personType, "%"+q.toUpperCase()+ "%");
    }

    @GetMapping("/{id}")
    private EmployeeDTO findById(@PathVariable("id") String id) {
        return personDataRepository
                .findById(id)
                .map(EmployeeDTO::new)
                .orElseThrow(() -> new DataNotFoundException("data tidak ditemukan"));
    }
}
