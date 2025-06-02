package id.smartpesantren.web;

import id.smartpesantren.dto.TahfidzTargetDTO;
import id.smartpesantren.entity.AcademicYear;
import id.smartpesantren.entity.TahfidzKonversi;
import id.smartpesantren.entity.TahfidzTargetSantri;
import id.smartpesantren.repository.TahfidzTargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pengasuhan/tahfidz/target-santri")
public class TahfidzTargetSantriResouce {
    @Autowired
    TahfidzTargetRepository repository;

    @GetMapping("by-classroom/{classRoomId}")
    public ResponseEntity<List<TahfidzTargetDTO>> findByClassRoomId(@PathVariable("classRoomId") String classRoomId) {
        return ResponseEntity.ok(repository.findByClassRoomId(classRoomId)) ;
    }

    @PostMapping
    public void createOrUpdateTarget(@RequestBody List<TahfidzTargetDTO> list) {
        List<TahfidzTargetSantri> datas = new ArrayList<>();
        for(TahfidzTargetDTO dto: list) {
            if(dto.getId() == null) {
                TahfidzTargetSantri d = new TahfidzTargetSantri();
//                d.setAcademicYear(new AcademicYear(dto.getAcademicYearId()));
//                d.setStudent(new Student(dto.getStudentId()));
//                d.setTarget(dto.getTargetId() == null? null: new TahfidzKonversi(dto.getTargetId()));
//                datas.add(d);
            } else {
                TahfidzTargetSantri d = repository.getOne(dto.getStudentId());
                d.setTarget(dto.getTargetId() == null? null: new TahfidzKonversi(dto.getTargetId()));
                datas.add(d);
            }
        }
        repository.saveAll(datas);
    }
}
