package id.smartpesantren.service;

import id.smartpesantren.entity.*;
import id.smartpesantren.repository.PersonDataRepository;
import id.smartpesantren.web.rest.errors.DataNotFoundException;
import id.smartpesantren.web.rest.vm.EmployeeVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    PersonDataRepository personDataRepository;

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
        pd.setJoinDate(vm.getJoinDate());
        pd.setEmployeeStatus(vm.getStatusId() == null? null: new EmployeeStatus(vm.getStatusId()));
        pd.setOrganization(vm.getOrganizationId() == null? null: new Organization(vm.getOrganizationId()));
        pd.setSection(vm.getSectionId() == null? null: new Section(vm.getSectionId()));
        pd.setJobPosition(vm.getJobPositionId() == null? null: new JobPosition(vm.getJobPositionId()));
        pd.setManager(vm.getManagerId() == null? null: new PersonData(vm.getManagerId()));
        pd.setMaritalStatus(vm.getMaritalStatusId() == null? null: new MaritalStatus(vm.getMaritalStatusId()));
        pd.setPhone(vm.getPhone());
        pd.setEmail(vm.getEmail());
        pd.setPermanentAddress(vm.getPermanentAddress());
        pd.setPermanentRT(vm.getPermanentRT());
        pd.setPermanentRW(vm.getPermanentRW());
        pd.setPermanentSubDistrict(vm.getPermanentSubdistrictId() == null? null: new SubDistrict(vm.getPermanentSubdistrictId()));
        pd.setResidentialAddress(vm.getResidentialAddress());
        pd.setResidentialRT(vm.getResidentialRT());
        pd.setResidentialRW(vm.getResidentialRW());
        pd.setResidentalSubDistrict(vm.getResidentialSubdistrictId() == null? null: new SubDistrict(vm.getResidentialSubdistrictId()));
        return pd;
    }

}
