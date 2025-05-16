package id.smartpesantren.web;

import id.smartpesantren.dto.SettingPenilaianVM;
import id.smartpesantren.entity.SettingPenilaian;
import id.smartpesantren.repository.SettingPenliaianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/setting/penilaian")
public class SettingPenilaianResource {
    @Autowired
    SettingPenliaianRepository repository;

    @GetMapping("/{institutionId}")
    public SettingPenilaianVM findByInstitutionId(@PathVariable("institutionId") String institutionId) {
        return repository.findByInstitutionId(institutionId);
    }

    @GetMapping("/all")
    public Iterable<SettingPenilaianVM> findAll() {
        return repository.findAll().stream().map(SettingPenilaianVM::new).collect(Collectors.toList());
    }

    @PutMapping
    public void save(@RequestBody SettingPenilaianVM vm) {
        repository.save(new SettingPenilaian(vm));
    }

}
