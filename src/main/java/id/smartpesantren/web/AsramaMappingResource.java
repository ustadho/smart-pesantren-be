package id.smartpesantren.web;

import id.smartpesantren.dto.AsramaMappingDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.AsramaMappingRepository;
import id.smartpesantren.service.AsramaMappingService;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.AsramaMappingVM;
import id.smartpesantren.web.rest.vm.AsramaMappingVMStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/pengasuhan/asrama-mapping")
public class AsramaMappingResource {
    @Autowired
    AsramaMappingRepository repository;

    @Autowired
    AsramaMappingService service;

    @GetMapping("")
    public Page<AsramaMappingDTO> filter(@RequestParam(value = "academicYear", defaultValue = "") String academicYear,
                                         @RequestParam(value = "pesantren", defaultValue = "") String pesantren,
                                         @RequestParam(value = "q", defaultValue = "") String q,
                                         Pageable pageable) {
        return repository.filter(
                academicYear,
                pesantren,
                pageable
        );
    }

    @GetMapping("/{asramaId}/{academicYearId}")
    public AsramaMappingVM findByAsramaAndAcademicYear(@PathVariable("asramaId") String asramaId, @PathVariable("academicYearId") String academicYearId) {
        return service.findByAsramaAndYearId(asramaId, academicYearId);
    }

    @GetMapping("/{id}")
    public AsramaMappingVM findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @PutMapping
    @Transactional
    public void save(@RequestBody @Valid AsramaMappingVM vm) {
        if(vm == null) {
            //Cek dulu sebelum diinsert
            AsramaMapping cr = repository.findTop1ByAsramaAndAcademicYear(new Asrama(vm.getAsramaId()), new AcademicYear(vm.getAcademicYearId()));
            if (cr != null) {
                throw new InternalServerErrorException("Data untuk asrama ini sudah dimasukkan");
            }
        }
        for (AsramaMappingVMStudent s: vm.getStudents()) {
            if(s.getId() == null) {
                String asrama = repository.findByStudentAndAcademicYear(s.getStudentId(), vm.getAcademicYearId());
                if (asrama != null) {
                    throw new InternalServerErrorException("Santri tersebut sudah dimasukkan di asrama: " + asrama);
                }
            }
        }
        service.save(vm);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        repository.deleteById(id);
    }

}
