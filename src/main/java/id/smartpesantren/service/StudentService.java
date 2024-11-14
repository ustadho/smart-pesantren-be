package id.smartpesantren.service;

import id.smartpesantren.entity.*;
import id.smartpesantren.repository.StudentRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.StudentVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository repository;

    public StudentVM saveOrUpdate(StudentVM vm) {
        Student s = fromVM(vm);
        repository.save(s);
        vm.setId(s.getId());
        return vm;
    }

    public Student fromVM(StudentVM vm) {
        Student s = null;
        if(vm.getId() != null) {
            Optional<Student> exist = repository.findById(vm.getId());
            if(exist.isPresent()) {
                s = exist.get();
            } else {
                throw new InternalServerErrorException("Student dengan id tersebut tidak ditemukan");
            }
        } else {
            s = new Student();
        }

        s.setId(vm.getId());
        s.setJoinYear(new AcademicYear(vm.getJoinYearId()));
        s.setCategory(new StudentCategory(vm.getCategoryId()));
        s.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        s.setNis(vm.getNis());
        s.setNisn(vm.getNisn());
        s.setNik(vm.getNik());
        s.setName(vm.getName());
        s.setEmail(vm.getEmail());
        s.setSex(vm.getSex());
        s.setPob(vm.getPobId()==null? null: new City(vm.getPobId()));
        s.setDob(vm.getDob());
        s.setCertificateNo(vm.getCertificateNo());
        s.setBloodType(vm.getBloodType());
        s.setChildNo(vm.getChildNo());
        s.setNumberOfSibling(vm.getNumberOfSibling());
        s.setReligion(vm.getReligionId() == null? null: new Religion(vm.getReligionId()));
        s.setNationality(vm.getNationalityId() == null? null: new Country(vm.getNationalityId()));
        s.setAddress1(vm.getAddress1());
        s.setAddress2(vm.getAddress2());
        s.setAddress3(vm.getAddress3());
        s.setRt(vm.getRt());
        s.setRw(vm.getRw());
        s.setSubDistrict(vm.getSubDistrictId() == null? null: new SubDistrict(vm.getSubDistrictId()));
        s.setPostalCode(vm.getPostalCode());
        s.setKksNo(vm.getKksNo());
        s.setYatim(vm.getYatim());
        s.setHeight(vm.getHeight());
        s.setWeight(vm.getWeight());
        s.setOriginSchool(vm.getOriginSchoolId() == null? null: new ReferalInstitution(vm.getOriginSchoolId()));
        s.setExamParticipantNo(vm.getExamParticipantNo());
        s.setCertificateNo(vm.getCertificateNo());
        s.setSkhunNo(vm.getSkhunNo());
        s.setFather(vm.getFatherId() == null? null: new PersonData(vm.getFatherId()));
        s.setMother(vm.getMotherId() == null? null: new PersonData(vm.getMotherId()));
        s.setFatherGuardian(vm.getFatherGuardianId() == null? null: new PersonData(vm.getFatherGuardianId()));
        s.setMotherGuardian(vm.getMotherGuardianId() == null? null: new PersonData(vm.getMotherGuardianId()));
        s.setStatus(vm.getStatus());
        s.setPhoto(vm.getPhoto());
        s.setNotes(vm.getNotes());
        return s;
    }

    public StudentVM fromStudent(Student st) {
        StudentVM vm = new StudentVM();
        vm.setId(st.getId());
        vm.setJoinYearId(st.getJoinYear() == null? null: st.getJoinYear().getId());
        vm.setCategoryId(st.getCategory() == null? null: st.getCategory().getId());
        vm.setNis(st.getNis());
        vm.setNisn(st.getNisn());
        vm.setNik(st.getNik());
        vm.setName(st.getName());
        vm.setEmail(st.getEmail());
        vm.setSex(st.getSex());
        vm.setPobId(st.getPob()==null? null: st.getPob().getId());
        vm.setDob(st.getDob());
        vm.setCertificateNo(st.getCertificateNo());
        vm.setBloodType(st.getBloodType());
        vm.setChildNo(st.getChildNo());
        vm.setNumberOfSibling(st.getNumberOfSibling());
        vm.setReligionId(st.getReligion() == null? null: st.getReligion().getId());
        vm.setNationalityId(st.getNationality() == null? null: st.getNationality().getId());
        vm.setAddress1(st.getAddress1());
        vm.setAddress2(st.getAddress2());
        vm.setAddress3(st.getAddress3());
        vm.setRt(st.getRt());
        vm.setRw(st.getRw());
        vm.setSubDistrictId(st.getSubDistrict() == null? null: st.getSubDistrict().getId());
        vm.setPostalCode(st.getPostalCode());
        vm.setKksNo(st.getKksNo());
        vm.setYatim(st.getYatim());
        vm.setHeight(st.getHeight());
        vm.setWeight(st.getWeight());
        vm.setOriginSchoolId(st.getOriginSchool() == null? null: st.getOriginSchool().getId());
        vm.setExamParticipantNo(st.getExamParticipantNo());
        vm.setCertificateNo(st.getCertificateNo());
        vm.setSkhunNo(st.getSkhunNo());
        vm.setFatherId(st.getFather() == null? null: st.getFather().getId());
        vm.setMotherId(st.getMother() == null? null: st.getMother().getId());
        vm.setFatherGuardianId(st.getFatherGuardian() == null? null: st.getFatherGuardian().getId());
        vm.setMotherGuardianId(st.getMotherGuardian() == null? null: st.getMotherGuardian().getId());
        vm.setStatus(st.getStatus());
        vm.setPhoto(st.getPhoto());
        vm.setNotes(st.getNotes());
        return vm;
    }
}
