package id.smartpesantren.web;

import id.smartpesantren.repository.PresensiKegiatanRepository;
import id.smartpesantren.service.PresensiKegiatanService;
import id.smartpesantren.web.rest.vm.PresensiKegiatanVM;
import id.smartpesantren.web.rest.vm.StudentPresenceVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@RestController
@RequestMapping("api/pengasuhan/presensi-kegiatan")
public class PresensiKegiatanResource {

    @Autowired
    PresensiKegiatanService service;

    @Autowired
    PresensiKegiatanRepository repository;

    @GetMapping
    public Page<PresensiKegiatanVM> filter(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
                                          @RequestParam("studentId") String studentId, @RequestParam("jenisKegiatanId") String jenisKegiatanId, Pageable p) throws ParseException {
        if (startDate != null && endDate != null) {
        }
        return repository.filter(
                startDate.equalsIgnoreCase("")? null: format.parse(startDate),
                endDate.equalsIgnoreCase("")? null: format.parse(endDate),
                jenisKegiatanId.equalsIgnoreCase("") || jenisKegiatanId.equalsIgnoreCase("0")? null: Integer.parseInt(jenisKegiatanId),
                studentId.equalsIgnoreCase("")? null: studentId,
                p
        );
    }
    @PostMapping
    public PresensiKegiatanVM create(@Valid @RequestBody PresensiKegiatanVM vm) {
        return service.save(vm);
    }

    @PutMapping
    public PresensiKegiatanVM update(@Valid @RequestBody PresensiKegiatanVM vm) {
        return service.save(vm);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }


    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
}
