package id.smartpesantren.web;

import id.smartpesantren.dto.MutabaahHistoryQuery;
import id.smartpesantren.repository.MutabaahRepository;
import id.smartpesantren.service.MutabaahService;
import id.smartpesantren.web.rest.vm.MutabaahUjianVM;
import id.smartpesantren.web.rest.vm.MutabaahVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/pengasuhan/mutabaah")
public class MutabaahResource {
    @Autowired
    private MutabaahService mutabaahService;

    @Autowired
    private MutabaahRepository repository;

    @PostMapping
    private void createMutabaah(@RequestBody MutabaahVM vm) {
        mutabaahService.save(vm);
    }

    @PostMapping("ujian")
    private void createOrUpdateUjian(@RequestBody MutabaahUjianVM vm) {
        mutabaahService.saveUjian(vm);
    }

    @GetMapping("history")
    private Page<MutabaahHistoryQuery> findHistory(@RequestParam(value = "santriId", required = false, defaultValue = "") String santriId,
                                                   @RequestParam(value = "fromDate", required = false, defaultValue = "") String fromDate,
                                                   @RequestParam(value = "toDate", required = false, defaultValue = "") String toDate,
                                                   @RequestParam(value = "waktuId", required = false, defaultValue = "") String waktuId,
                                                   @RequestParam(value = "tipe", required = false, defaultValue = "") String tipe,
                                                   @RequestParam(value = "createdBy", required = false, defaultValue = "") String createdBy,
                                                   @RequestParam(value = "q", required = false, defaultValue = "") String q,
                                                   Pageable p) throws ParseException {
        return repository.findHistory(santriId,
                fromDate == null? null: fromDate,
                toDate==null? null: toDate,
                waktuId.equalsIgnoreCase("")? 0: Integer.parseInt(waktuId),
                tipe, createdBy, q, p);

    }

    private SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
}
