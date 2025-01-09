package id.smartpesantren.web;

import id.smartpesantren.dto.AcademicActivityTimeDTO;
import id.smartpesantren.entity.AcademicActivityTime;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.Institution;
import id.smartpesantren.repository.AcademicActivityTimeRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.service.AcademicActivityTimeService;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/academic/activity-time")
public class AcademicActivityTimeResource {
    @Autowired
    AcademicActivityTimeService service;

    @Autowired
    AcademicActivityTimeRepository academicActivityTimeRepository;

    @GetMapping
    public Page<AcademicActivityTimeDTO> filter(@RequestParam("iid") String institutionId,
                                                @RequestParam("sex") String sex,
                                                Pageable p) {
        return academicActivityTimeRepository.filter(institutionId, sex, p);
    }

    @GetMapping("/all")
    public List<AcademicActivityTimeDTO> findAll(@RequestParam("iid") String institutionId, @RequestParam("sex") String sex) {
        return academicActivityTimeRepository.findAllActivityTime(institutionId == null? null: institutionId, sex);
    }

    @PostMapping
    public AcademicActivityTimeDTO create(@RequestBody @Valid AcademicActivityTimeDTO dto) {
        Optional<AcademicActivityTime> existSeq = academicActivityTimeRepository.findByFoundationAndInstitutionAndSexAndSeq(
            new Foundation(SecurityUtils.getFoundationId().get()),
            new Institution(dto.getInstitutionId()),
            dto.getSex(),
            dto.getSeq()
        );
        if (existSeq.isPresent()) {
            throw new InternalServerErrorException("Jam ke:"+dto.getSeq()+" tersebut sudah pernah dibuat");
        }
        boolean isOverlapping = service.isOverlapping(
                new Foundation(SecurityUtils.getFoundationId().get()),
                new Institution(dto.getInstitutionId()),
                dto.getSex(),
                dto.getStartTime(),
                dto.getEndTime()
        );

        if (isOverlapping) {
            throw new InternalServerErrorException("Ada irisan dari jam awal dan akhir");
        }
        return service.createOrUpdate(dto);
    }

    @PostMapping("/copy")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid List<AcademicActivityTimeDTO> dtos) {
        for(AcademicActivityTimeDTO dto: dtos) {
            Optional<AcademicActivityTime> existSeq = academicActivityTimeRepository.findByFoundationAndInstitutionAndSexAndSeq(
                new Foundation(SecurityUtils.getFoundationId().get()),
                new Institution(dto.getInstitutionId()),
                dto.getSex(),
                dto.getSeq()
            );
            if (existSeq.isPresent()) {
                throw new InternalServerErrorException("Jam ke:"+dto.getSeq()+" tersebut sudah pernah dibuat");
            }
            boolean isOverlapping = service.isOverlapping(
                new Foundation(SecurityUtils.getFoundationId().get()),
                new Institution(dto.getInstitutionId()),
                dto.getSex(),
                dto.getStartTime(),
                dto.getEndTime()
            );

            if (isOverlapping) {
                throw new InternalServerErrorException("Ada irisan dari jam awal dan akhir");
            }
        }
        service.copy(dtos);
    }

    @GetMapping("/{id}")
    public AcademicActivityTimeDTO findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public AcademicActivityTimeDTO update(@PathVariable String id, @RequestBody @Valid AcademicActivityTimeDTO dto) {
        dto.setId(id);
        Optional<AcademicActivityTime> ct = academicActivityTimeRepository.findById(id);
        if(!ct.isPresent()) {
            throw new InternalServerErrorException("Data tidak ditemukan!");
        }
        Optional<AcademicActivityTime> existSeq = academicActivityTimeRepository.findByFoundationAndInstitutionAndSexAndSeq(
            new Foundation(SecurityUtils.getFoundationId().get()),
            new Institution(dto.getInstitutionId()),
            dto.getSex(),
            dto.getSeq()
        );
        if (existSeq.isPresent() && !existSeq.get().getId().equalsIgnoreCase(ct.get().getId()) ) {
            throw new InternalServerErrorException("Jam tersebut sudah pernah dibuat");
        }
        return service.createOrUpdate(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        academicActivityTimeRepository.deleteById(id);
    }

}
