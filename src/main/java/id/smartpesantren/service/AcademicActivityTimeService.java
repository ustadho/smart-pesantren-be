package id.smartpesantren.service;

import id.smartpesantren.dto.AcademicActivityTimeDTO;
import id.smartpesantren.entity.AcademicActivityTime;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.Institution;
import id.smartpesantren.repository.AcademicActivityTimeRepository;
import id.smartpesantren.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AcademicActivityTimeService {
    @Autowired
    AcademicActivityTimeRepository repository;

    public AcademicActivityTimeDTO createOrUpdate(AcademicActivityTimeDTO dto) {
        AcademicActivityTime at = null;
        if(dto.getId() != null) {
            at = repository.findById(dto.getId()).get();
        } else {
            at = new AcademicActivityTime();
            at.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        }
        at.setInstitution(new Institution(dto.getInstitutionId()));
        at.setSex(dto.getSex());
        at.setSeq(dto.getSeq());
        at.setStartTime(dto.getStartTime());
        at.setEndTime(dto.getEndTime());
        at.setDescription(dto.getDescription());

        repository.save(at);
        return dto;
    }

    @Transactional
    public void copy(List<AcademicActivityTimeDTO> dtos) {
        for(AcademicActivityTimeDTO dto: dtos) {
            AcademicActivityTime at = null;
            if (dto.getId() != null) {
                at = repository.findById(dto.getId()).get();
            } else {
                at = new AcademicActivityTime();
                at.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
            }
            at.setInstitution(new Institution(dto.getInstitutionId()));
            at.setSex(dto.getSex());
            at.setSeq(dto.getSeq());
            at.setStartTime(dto.getStartTime());
            at.setEndTime(dto.getEndTime());
            at.setDescription(dto.getDescription());

            repository.save(at);
        }
    }

    public AcademicActivityTimeDTO findById(String id) {
        Optional<AcademicActivityTime> ac = repository.findById(id);
        AcademicActivityTimeDTO dto = null;
        if(ac.isPresent()) {
            dto = new AcademicActivityTimeDTO();
            dto.setId(ac.get().getId());
            dto.setInstitutionId(ac.get().getInstitution().getId());
            dto.setInstitutionName(ac.get().getInstitution().getName());
            dto.setSex(ac.get().getSex());
            dto.setSeq(ac.get().getSeq());
            dto.setStartTime(ac.get().getStartTime());
            dto.setEndTime(ac.get().getEndTime());
            dto.setDescription(ac.get().getDescription());
        }

        return dto;
    }

    public boolean isOverlapping(Foundation foundation, Institution institution, String sex, String startTime, String endTime) {
        long count = repository.countOverlappingTimes(foundation, institution, sex, startTime, endTime);
        return count > 0;
    }
}
