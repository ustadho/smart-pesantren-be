package id.smartpesantren.web;

import id.smartpesantren.dto.HalaqohDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.HalaqohRepository;
import id.smartpesantren.service.AsramaMappingService;
import id.smartpesantren.service.HalaqohService;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.AsramaMappingVM;
import id.smartpesantren.web.rest.vm.AsramaMappingVMStudent;
import id.smartpesantren.web.rest.vm.HalaqohVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pengasuhan/halaqoh")
public class HalaqohResource {
    @Autowired
    HalaqohRepository repository;

    @Autowired
    HalaqohService service;

    @GetMapping("")
    public Page<HalaqohDTO> filter(@RequestParam(value = "academicYear", defaultValue = "") String academicYear,
                                   @RequestParam(value = "pesantren", defaultValue = "") String pesantren,
                                   @RequestParam(value = "q", defaultValue = "") String q,
                                   Pageable pageable) {
        return repository.filter(
                academicYear,
                pesantren,
                pageable
        );
    }

    @GetMapping("/{pessnantrenId}/{academicYearId}")
    public HalaqohVM findByPesantrenAndAcademicYear(@PathVariable("pesantrenId") String pesantrenId, @PathVariable("academicYearId") String academicYearId) {
        return service.findByPesantrenAndYearId(pesantrenId, academicYearId);
    }

    @GetMapping("/{id}")
    public HalaqohVM findById(@PathVariable("id") String id) {
        return repository.findByHalaqohId(id).map(service::toVM).orElse(null);
    }

    @PutMapping
    @Transactional
    public void save(@RequestBody @Valid HalaqohVM vm) {
        if(vm == null) {
            //Cek dulu sebelum diinsert
            Halaqoh cr = repository.findTop1ByPesantrenAndAcademicYear(new Pesantren(vm.getPesantrenId()), new AcademicYear(vm.getAcademicYearId()));
            if (cr != null) {
                throw new InternalServerErrorException("Data untuk asrama ini sudah dimasukkan");
            }
        }
        for (AsramaMappingVMStudent s: vm.getStudents()) {
            if(s.getId() == null) {
                String asrama = repository.findByStudentAndAcademicYear(s.getStudentId(), vm.getAcademicYearId());
                if (asrama != null) {
                    throw new InternalServerErrorException("Santri '"+s.getStudentName()+"' sudah dimasukkan di asrama: " + asrama);
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
