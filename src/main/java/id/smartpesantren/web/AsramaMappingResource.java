package id.smartpesantren.web;

import id.smartpesantren.dto.AsramaMappingDTO;
import id.smartpesantren.dto.ClassRoomDTO;
import id.smartpesantren.entity.AsramaMappingStudent;
import id.smartpesantren.entity.ClassRoomStudent;
import id.smartpesantren.repository.AsramaMappingRepository;
import id.smartpesantren.repository.AsramaRepository;
import id.smartpesantren.service.AsramaMappingService;
import id.smartpesantren.web.rest.vm.AsramaMappingVM;
import id.smartpesantren.web.rest.vm.AsramaMappingVMStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public AsramaMappingVM findByAsramaAndAcademicYear(@PathVariable("asramaId") String id, @PathVariable("academicYearId") String academicYearId) {
        return service.findById(id);
    }

    @GetMapping("/{id}")
    public AsramaMappingVM findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @PutMapping
    @Transactional
    public void save(@RequestBody @Valid AsramaMappingVM vm) {
        //Cek dulu sebelum diinsert
//        Optional<AsramaMapping> cr = asramaRepository.findById(vm.getId());
//        if(!cr.isPresent()) {
//            throw new DataNotFoundException("Tahun akademik tidak dikenal");
//        }
        for (AsramaMappingVMStudent d: vm.getStudents()) {
            if(d.getId() == null) {
                AsramaMappingStudent cs = repository.findByStudentAndAcademicYear(d.getStudentId(), vm.getAcademicYearId());
                if (cs != null) {
                    throw new DuplicateKeyException("Santri tersebut sudah dimasukkan di asrama: " + cs.getAsramaMapping().getAsrama().getName());
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
