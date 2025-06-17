package id.smartpesantren.web;

import id.smartpesantren.entity.TahfidzKonversi;
import id.smartpesantren.repository.TahfidzKonversiRepository;
import id.smartpesantren.service.dto.TahfidzKonversiQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tahfidz-konversi")
public class TahfidzKonversiResouce {
    @Autowired
    TahfidzKonversiRepository tahfidzKonversiRepository;

    @GetMapping("/all")
    public ResponseEntity<List<TahfidzKonversi>> getAllTahfidzKonversi(){
        return ResponseEntity.ok(tahfidzKonversiRepository.findAllKonversi());
    }

    @GetMapping("/all-greater-than/{jml}")
    public List<TahfidzKonversi> getAllTahfidzKonversiGreaterThan(@PathVariable("jml") Integer jumlah){
        return tahfidzKonversiRepository.findAllKonversiGreaterThanJml(jumlah);
    }

    @GetMapping("{id}")
    public ResponseEntity<TahfidzKonversi> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(tahfidzKonversiRepository.findById(id).get());
    }

    @GetMapping("/by-jumlah-halaman/{jml}")
    public ResponseEntity<TahfidzKonversi> findByJumlahHalaman(@PathVariable("jml") Integer jumlah){
        return ResponseEntity.ok(tahfidzKonversiRepository.findByJmlHalaman(jumlah).get());
    }

}
