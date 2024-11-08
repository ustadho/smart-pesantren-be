package id.smartpesantren.web;

import id.smartpesantren.entity.Holiday;
import id.smartpesantren.repository.HolidayRepository;
import id.smartpesantren.web.rest.errors.DataNotFoundException;
import id.smartpesantren.web.rest.vm.HRHolidayVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/holiday")
public class HolidayResource {
    @Autowired
    HolidayRepository repository;

    @PostMapping
    public Holiday create(@RequestBody @Valid Holiday vm) {
        Holiday h = new Holiday();
        h.setName(vm.getName());
        h.setFixDate(vm.getFixDate());
        h.setType(vm.getType());

        repository.save(h);
        return h;
    }

    @PutMapping("/{id}")
    public Holiday create(@PathVariable("id") Integer id, @RequestBody @Valid Holiday vm) {
        Optional<Holiday> exist = repository.findById(id);
        if(!exist.isPresent()) {
            throw new DataNotFoundException("data libur dengan id ini tidak ditemukan");
        }
        Holiday h = exist.get();
        h.setName(vm.getName());
        h.setFixDate(vm.getFixDate());
        h.setType(vm.getType());

        repository.save(h);
        return h;
    }

    @GetMapping
    public Page<Holiday> filter(Pageable p) {
        return repository.findAll(p);
    }

    @GetMapping("/all")
    public List<Holiday> findAll() {
        return repository.findAll();
    }


}
