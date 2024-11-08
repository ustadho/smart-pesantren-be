package id.smartpesantren.web;

import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.HRHoliday;
import id.smartpesantren.entity.Holiday;
import id.smartpesantren.repository.HRHolidayRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.*;
import id.smartpesantren.web.rest.vm.HRHolidayVM;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hr/holiday")
public class HRHolidayResource {
    @Autowired
    HRHolidayRepository repository;

    @PostMapping
    public void create(@Valid @RequestBody HRHolidayVM vm) {
//        Optional<HRHoliday> exist = repository.findByEventDate(vm.getEventDate());
//        if(exist.isPresent()) {
//            throw new AlreadyExistException("Tanggal tersebut sudah pernah dimasukkan", "holiday");
//        }

        HRHoliday hh = new HRHoliday();
        hh.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        hh.setHoliday(new Holiday(vm.getHolidayId()));
        hh.setEventDate(vm.getEventDate());
        hh.setDescription(vm.getDescription());
        repository.save(hh);
    }

    @PutMapping("/{id}")
    public void create(@PathVariable("id") String id, @Valid @RequestBody HRHolidayVM vm) {
        Optional<HRHoliday> exist = repository.findById(id);
        if(!exist.isPresent()) {
            throw new DataNotFoundException("data dengan id tersebut tidak ditemukan");
        }
//        if(exist.get().getEventDate().compareTo(vm.getEventDate()) != 0) {
//            exist = repository.findByEventDate(vm.getEventDate());
//
//            if(exist.isPresent()) {
//                throw new InternalServerErrorException("Tanggal tersebut sudah pernah dimasukkan");
//            }
//        }
        HRHoliday hh = exist.get();
        hh.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        hh.setHoliday(new Holiday(vm.getHolidayId()));
        hh.setEventDate(vm.getEventDate());
        hh.setDescription(vm.getDescription());
        repository.save(hh);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        Optional<HRHoliday> exist = repository.findById(id);
        if(!exist.isPresent()) {
            throw new DataNotFoundException("data dengan id tersebut tidak ditemukan");
        }
        repository.deleteById(id);
    }

    @GetMapping
    public Page<HRHolidayVM> filter(@RequestParam(value = "q", defaultValue = "") String q,
                                    @RequestParam(value = "y", defaultValue = "0") Integer y,
                                    Pageable p) {
        q = "%"+ q.toUpperCase()+"%";
        return repository.filter(y, q, p);
    }

    @GetMapping("all")
    public List<HRHolidayVM> filter(@RequestParam(value = "q", defaultValue = "") String q,
                                    @RequestParam(value = "y", defaultValue = "0") Integer y) {
        q = "%"+ q.toUpperCase()+"%";
        return repository.filter(y, q);
    }

    @GetMapping("/{id}")
    public HRHolidayVM findById(@PathVariable("id") String id) {
        return repository.findById(id).map(HRHolidayVM::new).orElseThrow(() -> new DataNotFoundException("data tidak ditemukan"));
    }
}
