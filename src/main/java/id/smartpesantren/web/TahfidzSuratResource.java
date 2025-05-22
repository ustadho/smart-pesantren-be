package id.smartpesantren.web;

import id.smartpesantren.entity.TahfidzKonversi;
import id.smartpesantren.entity.TahfidzSurat;
import id.smartpesantren.repository.TahfidzKonversiRepository;
import id.smartpesantren.repository.TahfidzSuratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/tahfidz")
public class TahfidzSuratResource {
    @Autowired
    TahfidzKonversiRepository tahfidzKonversiRepository;

    @Autowired
    TahfidzSuratRepository tahfidzSuratRepository;

    @GetMapping("surat/all")
    public Iterable<TahfidzSurat> findAllSurat() {
        return tahfidzSuratRepository.findAll();
    }

    @GetMapping("konversi")
    public TahfidzKonversi findByNoHalaman(String code) {
        Optional<TahfidzKonversi> findByNoHalaman = tahfidzKonversiRepository.findByNoHalaman(code);
        return findByNoHalaman.orElse(null);
    }
}
