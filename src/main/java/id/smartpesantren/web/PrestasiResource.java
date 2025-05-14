package id.smartpesantren.web;

import id.smartpesantren.dto.PrestasiDTO;
import id.smartpesantren.repository.PrestasiRepository;
import id.smartpesantren.service.PrestasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("api/pengasuhan/prestasi")
public class PrestasiResource {

    @Autowired
    PrestasiService service;

    @Autowired
    PrestasiRepository repository;

    @GetMapping
    public Page<PrestasiDTO> filter(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
                                   @RequestParam("studentId") String studentId, @RequestParam("jenisPrestasiId") String jenisPrestasiId, Pageable p) throws ParseException {
        return repository.filter(
                startDate.equalsIgnoreCase("")? null: format.parse(startDate),
                endDate.equalsIgnoreCase("")? null: format.parse(endDate),
                jenisPrestasiId.equalsIgnoreCase("") || jenisPrestasiId.equalsIgnoreCase("0")? null: jenisPrestasiId,
                studentId.equalsIgnoreCase("")? null: studentId,
                p
        );
    }
    @PostMapping
    public PrestasiDTO create(@Valid @RequestBody PrestasiDTO vm) {
        return service.save(vm);
    }

    @PutMapping
    public PrestasiDTO update(@Valid @RequestBody PrestasiDTO vm) {
        return service.save(vm);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
}
