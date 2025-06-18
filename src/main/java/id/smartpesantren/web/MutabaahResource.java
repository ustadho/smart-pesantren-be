package id.smartpesantren.web;

import id.smartpesantren.dto.MutabaahHistoryQuery;
import id.smartpesantren.repository.MutabaahRepository;
import id.smartpesantren.service.MutabaahService;
import id.smartpesantren.web.rest.vm.MutabaahVM;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("history")
    private List<MutabaahHistoryQuery> findHistory(@RequestParam(value = "santriId", defaultValue = "") String santriId,
                                                   @RequestParam(value = "fromDate", defaultValue = "") String fromDate,
                                                   @RequestParam(value = "toDate", defaultValue = "") String toDate,
                                                   @RequestParam(value = "waktuId", defaultValue = "") String waktuId,
                                                   @RequestParam(value = "tipe", defaultValue = "") String tipe,
                                                   @RequestParam(value = "createdBy", defaultValue = "") String createdBy) {
        try {
            return repository.findHistory(santriId,
                    fromDate.equalsIgnoreCase("")? null: ymd.parse(fromDate),
                    toDate.equalsIgnoreCase("")? null: ymd.parse(toDate),
                    waktuId.equalsIgnoreCase("")? 0: Integer.parseInt(waktuId),
                    tipe, createdBy);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
}
