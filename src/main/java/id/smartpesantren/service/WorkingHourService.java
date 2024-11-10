package id.smartpesantren.service;

import id.smartpesantren.dto.WorkingHourDTO;
import id.smartpesantren.dto.WorkingHourDetailDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.WorkingHourRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.CodeAlreadyUsedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class WorkingHourService {
    @Autowired
    WorkingHourRepository workingHourRepository;

    Logger logger = LoggerFactory.getLogger(WorkingHourService.class);

    public WorkingHourDTO createOrUpdate(WorkingHourDTO req, WorkingHour old) {
        WorkingHour a = null;
        if(old != null) {
            a = old;
        } else {
            a = new WorkingHour();
        }
        a.setCode(req.getCode());
        a.setName(req.getName());
        a.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        a.setDescription(req.getDescription());
        a.setColor(req.getColor());
        //TODO

        for (Iterator<WorkingHourDetail> iterator = a.getDetails().iterator(); iterator.hasNext();) {
            WorkingHourDetail d = iterator.next();
            boolean used = false;
            for(WorkingHourDetailDTO ri: req.getDetails()) {
                if(ri.getId() != null && ri.getId().equalsIgnoreCase(d.getId())) {
                    used = true;
                    break;
                }
            }
            if(!used) {
                iterator.remove();
            }
        }

        for(WorkingHourDetailDTO d: req.getDetails()){
            logger.debug("req.getItems ==>[{}]", d.getWorkingTimeId());
            WorkingHourDetail det = null;

            if(a.getId() == null) {
                det = new WorkingHourDetail();
            } else {
                if(d.getId() == null) {
                    det = new WorkingHourDetail();
                } else {
                    // Check if existing detail needs deletion
                    boolean existingDetailFound = false;
                    for (WorkingHourDetail existingDetail : a.getDetails()) {
                        System.out.println("existingDetail.id: " + existingDetail.getId());
                        System.out.println("d.getId(): " + d.getId());
                        if (existingDetail.getId().equals(d.getId())) {
                            det = existingDetail;
                            System.out.println("existingDetail.id: " + det.getId());
                            existingDetailFound = true;
                            break;
                        }
                    }
                    if(!existingDetailFound) {
                        System.out.println("detail not exists");
                    }
                }
            }

            det.setId(d.getId());
            det.setDay(new Day(d.getDayId()));
            det.setWorkingTime(new WorkingTime(d.getWorkingTimeId()));
            det.setWorkingHour(a);

            if(det.getId() == null) {
                a.getDetails().add(det);
            }

        }

        workingHourRepository.save(a);
        req.setId(a.getId());
        return req;
    }

}
