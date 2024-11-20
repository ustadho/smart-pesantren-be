package id.smartpesantren.service;

import id.smartpesantren.dto.GuardianDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.PersonDataRepository;
import id.smartpesantren.web.EmployementType;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.GuardianVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuardianService {
    @Autowired
    PersonDataRepository personDataRepository;

    public GuardianVM createOrUpdate(GuardianVM vm) {
        PersonData pd = fromVM(vm);
        personDataRepository.saveAndFlush(pd);
        vm.setId(pd.getId());
        return vm;
    }

    public Page<GuardianDTO> filter(String q, String sex, Pageable p) {
        return personDataRepository.filterGuardian(q, sex, p);
    }

    public PersonData fromVM(GuardianVM vm) {
        PersonData p = null;
        if(vm.getId() != null) {
            Optional<PersonData> pd = personDataRepository.findById(vm.getId());
            if(pd.isPresent()) {
               p = pd.get();
            } else {
                throw new InternalServerErrorException("Person data tidak ditemukan");
            }
        } else {
            p = new PersonData();
        }
        p.setName(vm.getName());
        p.setNik(vm.getNik());
        p.setTitle(vm.getTitle());
        p.setGuardian(true);
        p.setSex(vm.getSex());
        p.setDob(vm.getDob());
        p.setPob(vm.getPobId() == null? null: new City(vm.getPobId()));
        p.setReligion(vm.getReligionId() == null? null: new Religion(vm.getReligionId()));
        p.setNationality(vm.getNationalityId() == null? null: new Country(vm.getNationalityId()));
        p.setEmployementType(vm.getEmploymentTypeId() == null? null: new EmployementType(vm.getEmploymentTypeId()));
        p.setMonthlyRevenue(vm.getMonthlyRevenue());
        p.setMaritalStatus(vm.getMaritalStatusId() == null? null: new MaritalStatus(vm.getMaritalStatusId()));
        p.setEducationLevel(vm.getEducationLevelId() == null? null: new EducationLevel(vm.getEducationLevelId()));
        p.setPermanentAddress(vm.getPermanentAddress());
        p.setPermanentSubDistrict(vm.getPermanentSubdistrictId() == null? null: new SubDistrict(vm.getPermanentSubdistrictId()));
        p.setPermanentPostalCode(vm.getPermanentPostalCode());
        p.setResidentialAddress(vm.getResidentalAddress());
        p.setResidentialRT(vm.getResidentalRT());
        p.setResidentialRW(vm.getResidentalRW());
        p.setResidentalSubDistrict(vm.getResidentalSubdistrictId() == null? null: new SubDistrict(vm.getResidentalSubdistrictId()));
        p.setResidentalPostalCode(vm.getResidentalPostalCode());
        return p;
    }
}
