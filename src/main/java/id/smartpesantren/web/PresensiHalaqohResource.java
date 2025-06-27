package id.smartpesantren.web;

import id.smartpesantren.service.PresensiHalaqohService;
import id.smartpesantren.service.dto.PresensiHalaqohVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/pengasuhan/presensi-halaqoh")
public class PresensiHalaqohResource {
    @Autowired
    PresensiHalaqohService service;

    @PostMapping
    public PresensiHalaqohVM createPresensiHalaqoh(@RequestBody PresensiHalaqohVM vm) {
        return service.save(vm);
    }

    @GetMapping("/{tanggal}/{halaqohId}/{tahfidzTimeId}")
    public PresensiHalaqohVM findById(@PathVariable String tanggal, @PathVariable String halaqohId, @PathVariable Integer tahfidzTimeId) throws ParseException {
        return service.findByHalaqohIdAndTahfidzTimeId(halaqohId, tanggal, tahfidzTimeId);
    }


}
