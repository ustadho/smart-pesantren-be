package id.smartpesantren.service;

import id.smartpesantren.dto.EmployeeDayOffDTO;
import id.smartpesantren.entity.Day;
import id.smartpesantren.entity.PersonData;
import id.smartpesantren.entity.WorkingDayOff;
import id.smartpesantren.repository.WorkingDayOffRepository;
import id.smartpesantren.web.rest.vm.WorkingDayOffVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingDayOffService {
    @Autowired
    WorkingDayOffRepository workingDayOffRepository;

    public EmployeeDayOffDTO saveOrUpdate(WorkingDayOffVM vm) {
        WorkingDayOff wdo = workingDayOffRepository.findByEmployeeAndDay(
                new PersonData(vm.getEmployeeId()),
                new Day(vm.getDayId())
        );
        if(wdo == null) {
            wdo = new WorkingDayOff();
        }
        wdo.setEmployee(new PersonData(vm.getEmployeeId()));
        wdo.setDay(new Day(vm.getDayId()));
        if(vm.getNewValue().equalsIgnoreCase("Libur")) {
            workingDayOffRepository.save(wdo);
        } else if (wdo != null) {
            workingDayOffRepository.delete(wdo);
        }
        vm.setId(wdo.getId());
        return workingDayOffRepository.findByEmployeeId(vm.getEmployeeId());
    }
}
