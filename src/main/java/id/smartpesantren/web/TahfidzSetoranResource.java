package id.smartpesantren.web;

import id.smartpesantren.entity.TahfidzSetoran;
import id.smartpesantren.repository.TahfidzSetoranRepository;
import id.smartpesantren.service.TahfidzSetoranService;
import id.smartpesantren.service.dto.TahfidzSetoranVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/pengasuhan/tahfidz-setoran")
public class TahfidzSetoranResource {
    @Autowired
    TahfidzSetoranService service;

    @Autowired
    TahfidzSetoranRepository repository;

    @PostMapping
    public TahfidzSetoranVM createSetoran(@RequestBody TahfidzSetoranVM vm) {
        return service.createOrUpdate(vm);
    }

    @PutMapping("{id}")
    public TahfidzSetoranVM updateSetoran(@RequestBody TahfidzSetoranVM vm) {
        return service.createOrUpdate(vm);
    }

    @GetMapping()
    public Page<TahfidzSetoranVM> filter(@RequestParam("startDate") String startDate,
                                         @RequestParam("endDate") String endDate,
                                         @RequestParam("studentId") String studentId, Pageable p) throws ParseException {
        return repository.filter(startDate.equalsIgnoreCase("")? null: format.parse(startDate),
                endDate.equalsIgnoreCase("")? null: format.parse(endDate),
                studentId == null || studentId.equalsIgnoreCase("")? null: studentId, p);
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
}
