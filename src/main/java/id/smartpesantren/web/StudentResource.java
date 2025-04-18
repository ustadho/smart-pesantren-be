package id.smartpesantren.web;

import id.smartpesantren.dto.StudentDTO;
import id.smartpesantren.entity.Student;
import id.smartpesantren.repository.StudentRepository;
import id.smartpesantren.service.StudentService;
import id.smartpesantren.web.rest.errors.DataNotFoundException;
import id.smartpesantren.web.rest.utils.HeaderUtil;
import id.smartpesantren.web.rest.vm.StudentVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/academic/student")
public class StudentResource {
    @Autowired
    StudentService service;

    @Autowired
    StudentRepository repository;

    @GetMapping
    public Page<StudentDTO> filter(@RequestParam(value = "q", defaultValue = "") String q,
                                   @RequestParam(value = "iid", defaultValue = "") String institutionId,
                                   @RequestParam(value = "y", defaultValue = "") String academicYearId,
                                   @RequestParam(value = "c", defaultValue = "") String categoryId,
                                   @RequestParam(value = "sex", defaultValue = "") String sex,
                                   Pageable p) {
        return repository.filter("%"+q.toUpperCase()+"%",
                institutionId.equalsIgnoreCase("")? null: institutionId,
                academicYearId.equalsIgnoreCase("")? null: academicYearId,
                categoryId.equalsIgnoreCase("")? null: categoryId,
                sex,
                p);
    }

    @GetMapping("filter-all")
    public List<StudentDTO> filter(@RequestParam(value = "q", defaultValue = "") String q,
                                   @RequestParam(value = "iid", defaultValue = "") String institutionId,
                                   @RequestParam(value = "y", defaultValue = "") String academicYearId,
                                   @RequestParam(value = "c", defaultValue = "") String categoryId,
                                   @RequestParam(value = "sex", defaultValue = "") String sex) {
        return repository.filterAll("%"+q.toUpperCase()+"%",
                institutionId.equalsIgnoreCase("")? null: institutionId,
                academicYearId.equalsIgnoreCase("")? null: academicYearId,
                categoryId.equalsIgnoreCase("")? null: categoryId,
                sex);
    }

    @PostMapping
    public StudentVM create(@RequestBody @Valid StudentVM vm) {
        return service.saveOrUpdate(vm);
    }

    @PutMapping("/{id}")
    public StudentVM update(@PathVariable("id") String id, @RequestBody @Valid StudentVM vm) {
        Optional<Student> exist = repository.findById(id);
        if(!exist.isPresent()) {
            throw new DataNotFoundException("data santri tidak ditemukan!");
        }
        vm.setId(id);
        return service.saveOrUpdate(vm);
    }

    @GetMapping("/{id}")
    public StudentVM findById(@PathVariable("id") String id) {
        Optional<Student> exist = repository.findById(id);
        if(exist.isPresent()) {
            StudentVM vm = service.fromStudent(exist.get());
            return vm;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        Optional<Student> exist = repository.findById(id);
        if(!exist.isPresent()) {
            throw new DataNotFoundException("data santri tidak ditemukan!");
        }
        repository.deleteById(id);
    }

}
