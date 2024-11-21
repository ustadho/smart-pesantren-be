package id.smartpesantren.repository;

import id.smartpesantren.dto.EmployeeDTO;
import id.smartpesantren.dto.GuardianDTO;
import id.smartpesantren.entity.PersonData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonDataRepository extends JpaRepository<PersonData, String> {
    @Query("select new id.smartpesantren.dto.EmployeeDTO(a.id, ec.id, ec.name, " +
            "a.employeeNo, a.name, a.sex, a.nik, pob, a.dob, o.id, o.name, s.id, s.name, jp.id, jp.name, el.id, el.name, " +
            "a.permanentAddress, a.permanentRT, a.permanentRW, a.permanentSubDistrict.id, a.permanentSubDistrict.name, " +
            "a.residentialAddress, a.residentialRT, a.residentialRW, a.residentialSubDistrict.id, a.residentialSubDistrict.name, " +
            "a.majors, a.faculty, ri.id, ri.name, a.isGuardian, a.active) \n" +
            "from PersonData a \n" +
            "left join a.organization o \n" +
            "left join a.section s \n" +
            "left join a.jobPosition jp \n" +
            "left join a.employeeCategory ec \n" +
            "left join a.educationLevel el \n" +
            "left join a.pob pob \n" +
            "left join a.permanentSubDistrict psd \n" +
            "left join a.residentialSubDistrict rsd \n" +
            "left join a.referalInstitution ri \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and (coalesce(:unor,'')='' OR o.id=:unor) \n" +
            "and a.isEmployee = true \n" +
            "and upper(a.name) like :q ")
    Page<EmployeeDTO> filterEmployee(@Param("unor") String unor, @Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.GuardianDTO(a) \n" +
            "from PersonData a \n" +
            "left join a.organization o \n" +
            "left join a.section s \n" +
            "left join a.jobPosition jp \n" +
            "left join a.employeeCategory ec \n" +
            "left join a.educationLevel el \n" +
            "left join a.pob pob \n" +
            "left join a.permanentSubDistrict psd \n" +
            "left join a.residentialSubDistrict rsd \n" +
            "left join a.referalInstitution ri \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and a.isGuardian = true \n"+
            "and upper(a.name) like :q \n"+
            "and (coalesce(:sex,'')='' OR a.sex=:sex) "
    )
    Page<GuardianDTO> filterGuardian(@Param("q") String q, @Param("sex") String sex, Pageable p);
}
