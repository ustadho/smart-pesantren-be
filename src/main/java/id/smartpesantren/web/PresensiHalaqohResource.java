package id.smartpesantren.web;

import id.smartpesantren.service.PresensiHalaqohService;
import id.smartpesantren.service.dto.PresensiHalaqohSantriVM;
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

    @GetMapping("/{halaqohId}/{tanggal}/{tahfidzTimeId}")
    public PresensiHalaqohVM findByHalaqohAndTanggalAndTime(@PathVariable String halaqohId, @PathVariable String tanggal, @PathVariable Integer tahfidzTimeId) throws ParseException {
        return service.findByHalaqohIdAndTahfidzTimeId(halaqohId, tanggal, tahfidzTimeId);
    }

    @GetMapping("detail-santri/{presensiId}")
    public PresensiHalaqohSantriVM findPresensiSantri(@PathVariable("presensiId") String id) {
        return service.findHalaqohSantriByPresensiGuru(id);
    }

    @PostMapping("presensi-santri")
    public PresensiHalaqohSantriVM savePresensiSantri(@RequestBody PresensiHalaqohSantriVM vm) {
        return service.savePresensiHalaqohSantri(vm);
    }

}
