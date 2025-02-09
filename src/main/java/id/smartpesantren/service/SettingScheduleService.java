package id.smartpesantren.service;

import id.smartpesantren.entity.AcademicYear;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.Institution;
import id.smartpesantren.entity.SettingSchedule;
import id.smartpesantren.repository.SettingScheduleRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.SettingScheduleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingScheduleService {
    @Autowired
    SettingScheduleRepository settingScheduleRepository;

    public SettingScheduleVM createOrUpdate(SettingScheduleVM vm) {
        SettingSchedule ss = null;
        if(vm.getId() == null) {
            ss = new SettingSchedule();
            ss.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
            ss.setInstitution(new Institution(vm.getInstitutionId()));
            ss.setAcademicYear(new AcademicYear(vm.getAcademicYearId()));
            ss.setSex(vm.getSex());
            ss.setDescription(vm.getDescription());
        }
        return vm;
    }

}
