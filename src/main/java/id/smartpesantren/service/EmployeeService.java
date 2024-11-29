package id.smartpesantren.service;

import id.smartpesantren.entity.*;
import id.smartpesantren.repository.PersonDataRepository;
import id.smartpesantren.web.rest.vm.EmployeeVM;
import id.smartpesantren.web.rest.vm.EmployeeWorkingHourVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

@Service
public class EmployeeService {
    @Autowired
    PersonDataRepository personDataRepository;

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Transactional
    public EmployeeVM createOrUpdate(EmployeeVM vm, PersonData old) {
        PersonData p =convertFromVm(vm, old);
        personDataRepository.saveAndFlush(p);
        vm.setId(p.getId());
        return vm;
    }

    public PersonData convertFromVm(EmployeeVM vm, PersonData old) {
        PersonData pd = null;
        if(old != null) {
            pd = old;
        } else {
            pd = new PersonData();
        }
        pd.setName(vm.getName());
        pd.setEmployeeNo(vm.getEmployeeNo());
        pd.setSex(vm.getSex());
        pd.setEmployeeCategory(new EmployeeCategory(vm.getCategoryId()));
        pd.setNik(vm.getNik());
        pd.setPob(vm.getPobId()==null? null: new City(vm.getPobId()));
        pd.setDob(vm.getDob());
        pd.setReligion(vm.getReligionId() == null? null: new Religion(vm.getReligionId()));
        pd.setJoinDate(vm.getJoinDate());
        pd.setEmployeeStatus(vm.getStatusId() == null? null: new EmployeeStatus(vm.getStatusId()));
        pd.setOrganization(vm.getOrganizationId() == null? null: new Organization(vm.getOrganizationId()));
        pd.setSection(vm.getSectionId() == null? null: new Section(vm.getSectionId()));
        pd.setJobPosition(vm.getJobPositionId() == null? null: new JobPosition(vm.getJobPositionId()));
        pd.setManager(vm.getManagerId() == null? null: new PersonData(vm.getManagerId()));
        pd.setMaritalStatus(vm.getMaritalStatusId() == null? null: new MaritalStatus(vm.getMaritalStatusId()));
        pd.setPhone(vm.getPhone());
        pd.setEmail(vm.getEmail());
        pd.setWorkingHour(vm.getWorkingHourId()==null? null: new WorkingHour(vm.getWorkingHourId()));
        pd.setWorkingShift(vm.getWorkingShift());
        pd.setPermanentAddress(vm.getPermanentAddress());
        pd.setPermanentRT(vm.getPermanentRT());
        pd.setPermanentRW(vm.getPermanentRW());
        pd.setPermanentSubDistrict(vm.getPermanentSubdistrictId() == null? null: new SubDistrict(vm.getPermanentSubdistrictId()));
        pd.setResidentialAddress(vm.getResidentialAddress());
        pd.setResidentialRT(vm.getResidentialRT());
        pd.setResidentialRW(vm.getResidentialRW());
        pd.setResidentialSubDistrict(vm.getResidentialSubdistrictId() == null? null: new SubDistrict(vm.getResidentialSubdistrictId()));
        pd.setPhoto(vm.getPhoto());

        for (Iterator<EmployeeWorkingHour> iterator = pd.getWorkingHours().iterator(); iterator.hasNext();) {
            EmployeeWorkingHour d = iterator.next();
            boolean used = false;
            for(EmployeeWorkingHourVM ri: vm.getWorkingHours()) {
                if(ri.getId() != null && ri.getId().equalsIgnoreCase(d.getId())) {
                    used = true;
                    break;
                }
            }
            if(!used) {
                iterator.remove();
            }
        }

        for(EmployeeWorkingHourVM d: vm.getWorkingHours()){
            logger.debug("req.getItems ==>[{}]", d.getWorkingHourName());
            EmployeeWorkingHour det = null;

            if(pd.getId() == null) {
                det = new EmployeeWorkingHour();
            } else {
                if(d.getId() == null) {
                    det = new EmployeeWorkingHour();
                } else {
                    // Check if existing detail needs deletion
                    boolean existingDetailFound = false;
                    for (EmployeeWorkingHour existingDetail : pd.getWorkingHours()) {
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
            det.setEffectiveDate(d.getEffectiveDate());
            det.setWorkingHour(new WorkingHour(d.getWorkingHourId()));
            det.setEmployee(pd);

            if(det.getId() == null) {
                pd.getWorkingHours().add(det);
            }
        }

        return pd;
    }

}
