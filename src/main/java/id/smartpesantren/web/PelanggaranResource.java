package id.smartpesantren.web;

import id.smartpesantren.dto.PelanggaranDTO;
import id.smartpesantren.repository.PelanggaranRepository;
import id.smartpesantren.service.PelanggaranService;
import id.smartpesantren.web.rest.vm.PresensiKegiatanVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("api/pengasuhan/pelanggaran")
public class PelanggaranResource {

    @Autowired
    PelanggaranService service;

    @Autowired
    PelanggaranRepository repository;

    @GetMapping
    public Page<PelanggaranDTO> filter(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
                                       @RequestParam("studentId") String studentId, @RequestParam("jenisPelanggaranId") String jenisPelanggaranId, Pageable p) throws ParseException {
        if (startDate != null && endDate != null) {
        }
        return repository.filter(
                startDate.equalsIgnoreCase("")? null: format.parse(startDate),
                endDate.equalsIgnoreCase("")? null: format.parse(endDate),
                jenisPelanggaranId.equalsIgnoreCase("") || jenisPelanggaranId.equalsIgnoreCase("0")? null: Integer.parseInt(jenisPelanggaranId),
                studentId.equalsIgnoreCase("")? null: studentId,
                p
        );
    }
    @PostMapping
    public PelanggaranDTO create(@Valid @RequestBody PelanggaranDTO vm) {
        return service.save(vm);
    }

    @PutMapping
    public PelanggaranDTO update(@Valid @RequestBody PelanggaranDTO vm) {
        return service.save(vm);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }


    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
}
