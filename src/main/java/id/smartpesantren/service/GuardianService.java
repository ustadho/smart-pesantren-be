package id.smartpesantren.service;

import id.smartpesantren.constant.PersonType;
import id.smartpesantren.dto.GuardianDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.PersonDataRepository;
import id.smartpesantren.security.SecurityUtils;
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

    public PersonData createOrUpdate(GuardianVM vm) {
        PersonData pd = fromVM(vm);
        personDataRepository.saveAndFlush(pd);
        vm.setId(pd.getId());
        return pd;
    }

    public Page<GuardianDTO> filter(String q, String title, Pageable p) {
        return personDataRepository.filterGuardian(q, title, p);
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
        p.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        p.setPersonType(PersonType.GUARDIAN);
        p.setName(vm.getName());
        p.setNik(vm.getNik());
        p.setTitle(vm.getTitleId() == null? null: new PersonTitle(vm.getTitleId()));
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
        p.setPhone(vm.getPhone());
        p.setEmail(vm.getEmail());
        p.setPermanentAddress(vm.getPermanentAddress());
        p.setPermanentRT(vm.getPermanentRT());
        p.setPermanentRW(vm.getPermanentRW());
        p.setPermanentSubDistrict(vm.getPermanentSubdistrictId() == null? null: new SubDistrict(vm.getPermanentSubdistrictId()));
        p.setPermanentPostalCode(vm.getPermanentPostalCode());
        p.setResidentialAddress(vm.getResidentialAddress());
        p.setResidentialRT(vm.getResidentialRT());
        p.setResidentialRW(vm.getResidentialRW());
        p.setResidentialSubDistrict(vm.getResidentialSubdistrictId() == null? null: new SubDistrict(vm.getResidentialSubdistrictId()));
        p.setResidentialPostalCode(vm.getResidentialPostalCode());
        return p;
    }

    public GuardianVM fromPersonData(PersonData p) {
        GuardianVM vm = new GuardianVM();
        vm.setId(p.getId());
        vm.setName(p.getName());
        vm.setSex(p.getSex());
        vm.setTitleId(p.getTitle() == null ? null: p.getTitle().getId());
        vm.setNik(p.getNik());
        vm.setDob(p.getDob());
        vm.setPobId(p.getPob() == null? null: p.getPob().getId());
        vm.setNationalityId(p.getNationality() == null? null: p.getNationality().getId());
        vm.setEmploymentTypeId(p.getEmployementType() == null? null: p.getEmployementType().getId());
        vm.setMonthlyRevenue(p.getMonthlyRevenue());
        vm.setMaritalStatusId(p.getMaritalStatus() == null? null: p.getMaritalStatus().getId());
        vm.setEducationLevelId(p.getEducationLevel() == null? null: p.getEducationLevel().getId());
        vm.setReligionId(p.getReligion() == null? null: p.getReligion().getId());
        vm.setPhone(p.getPhone());
        vm.setEmail(p.getEmail());
        vm.setPermanentAddress(p.getPermanentAddress());
        vm.setPermanentRT(p.getPermanentRT());
        vm.setPermanentRW(p.getPermanentRW());
        vm.setPermanentSubdistrictId(p.getPermanentSubDistrict() ==null ? null: p.getPermanentSubDistrict().getId());
        vm.setPermanentPostalCode(p.getPermanentPostalCode());

        vm.setResidentialAddress(p.getResidentialAddress());
        vm.setResidentialRT(p.getResidentialRT());
        vm.setResidentialRW(p.getResidentialRW());
        vm.setResidentialSubdistrictId(p.getResidentialSubDistrict() ==null ? null: p.getResidentialSubDistrict().getId());
        vm.setResidentialPostalCode(p.getResidentialPostalCode());
        return vm;
    }
}
