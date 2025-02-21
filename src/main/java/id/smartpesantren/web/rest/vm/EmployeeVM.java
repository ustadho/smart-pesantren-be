package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EmployeeVM {
    private String id;

    @NotNull
    private String categoryId;

    @NotNull
    private Integer religionId;

    private String categoryName;

    @NotNull @NotEmpty
    private String employeeNo;

    @NotNull
    private String name;

    @NotNull
    private String sex;
    private String nik;
    private Integer pobId;

    @NotNull
    private Date dob;
    private Integer maritalStatusId;
    private String organizationId;
    private String organizationName;
    private String sectionId;
    private String sectionName;
    private String jobPositionId;
    private String jobPositionName;
    private Integer educationLevelId;
    private String educationLevelName;
    private String statusId;

    private Date joinDate;

    private String permanentAddress;
    private String permanentRT;
    private String permanentRW;
    private Integer permanentSubdistrictId;
    private String permanentSubdistrictName;

    private String residentialAddress; // Alamat domisili
    private String residentialRT;
    private String residentialRW;
    private Integer residentialSubdistrictId;
    private String residentialSubdistrictName;

    private String majors;
    private String faculty;
    private String referalInstitutionId;
    private String referalInstitutionName;

    private Boolean isGuardian;

    private Boolean active;

    @NotNull
    private String managerId;
    private String phone;
    private String email;
    private String workingHourId;
    public Boolean workingShift;
    private String photo;
    private Set<EmployeeFormalEducationVM> formalEducations = new HashSet<>();

    private Set<EmployeeFormalEducationVM> unorHistories = new HashSet<>();

    private Set<EmployeeWorkingHourVM> workingHours = new HashSet<>();

    public EmployeeVM() {
    }

    public EmployeeVM(PersonData p) {
        this.setId(p.getId());
        this.setCategoryId(p.getEmployeeCategory() == null? null: p.getEmployeeCategory().getId());
        this.setCategoryName(p.getEmployeeCategory() == null? null: p.getEmployeeCategory().getName());
        this.setEmployeeNo(p.getEmployeeCategory() == null? null: p.getEmployeeNo());
        this.setName(p.getName());
        this.setSex(p.getSex());
        this.setNik(p.getNik());
        this.setReligionId(p.getReligion() == null? null: p.getReligion().getId());
        this.setPobId(p.getPob()==null? null: p.getPob().getId());
        this.setDob(p.getDob());
        this.setMaritalStatusId(p.getMaritalStatus()==null? null: p.getMaritalStatus().getId());
        this.setOrganizationId(p.getOrganization() == null? null: p.getOrganization().getId());
        this.setOrganizationName(p.getOrganization() == null? null: p.getOrganization().getName());
        this.setSectionId(p.getSection()==null? null: p.getSection().getId());
        this.setSectionName(p.getSection()==null? null: p.getSection().getName());
        this.setJobPositionId(p.getJobPosition() == null? null: p.getJobPosition().getId());
        this.setJobPositionName(p.getJobPosition() == null? null: p.getJobPosition().getName());
        this.setEducationLevelId(p.getEducationLevel() == null? null: p.getEducationLevel().getId());
        this.setEducationLevelName(p.getEducationLevel() == null? null: p.getEducationLevel().getName());
        this.setPermanentAddress(p.getPermanentAddress());
        this.setPermanentRT(p.getPermanentRT());
        this.setPermanentRW(p.getPermanentRW());
        this.setPermanentSubdistrictId(p.getPermanentSubDistrict() == null? null: p.getPermanentSubDistrict().getId());
        this.setPermanentSubdistrictName(p.getPermanentSubDistrict() == null? null: p.getPermanentSubDistrict().getName());
        this.setResidentialAddress(p.getResidentialAddress());
        this.setResidentialRT(p.getResidentialRT());
        this.setResidentialRT(p.getResidentialRW());
        this.setResidentialSubdistrictId(p.getResidentialSubDistrict() == null? null: p.getResidentialSubDistrict().getId());
        this.setResidentialSubdistrictName(p.getResidentialSubDistrict() == null? null: p.getResidentialSubDistrict().getName());
        this.setMajors(p.getMajors());
        this.setFaculty(p.getFaculty());
        this.setReferalInstitutionId(p.getReferalInstitution() == null? null: p.getReferalInstitution().getId());
        this.setReferalInstitutionName(p.getReferalInstitution() == null? null: p.getReferalInstitution().getName());
        this.setGuardian(p.isGuardian);
        this.setStatusId(p.getEmployeeStatus()==null? null: p.getEmployeeStatus().getId());
        this.setManagerId(p.getManager()==null? null: p.getManager().getId());
        this.setActive(p.getActive());
        this.setWorkingHourId(p.getWorkingHour()==null? null: p.getWorkingHour().getId());
        this.setWorkingShift(p.getWorkingShift());
        this.setEmail(p.getEmail());
        this.setPhone(p.getPhone());
        this.setPhoto(p.getPhoto());
        for(EmployeeFormalEducaton e: p.getFormalEducations()) {
            EmployeeFormalEducationVM edu = new EmployeeFormalEducationVM();
            edu.setId(e.getId());
            edu.setInstitutionId(e.getInstitution()==null? null: e.getInstitution().getId());
            edu.setInstitutionName(e.getInstitution()==null? null: e.getInstitution().getName());
            edu.setEducationLevelId(e.getLevel()==null? null: e.getLevel().getId());
            edu.setEducationLevelName(e.getLevel()==null? null: e.getLevel().getName());
            edu.setFaculty(e.getFaculty());
            edu.setMajors(e.getMajors());
            edu.setStartYear(e.getStartYear());
            edu.setEndYear(e.getEndYear());
            edu.setDescription(e.getDescription());
            this.getFormalEducations().add(edu);
        }

        for(EmployeeWorkingHour e: p.getWorkingHours()) {
            EmployeeWorkingHourVM wh = new EmployeeWorkingHourVM();
            wh.setId(e.getId());
            wh.setEffectiveDate(e.getEffectiveDate());
            wh.setWorkingHourId(e.getWorkingHour().getId());
            wh.setWorkingHourName(e.getWorkingHour().getName());
            wh.setDescription(e.getDescription());
            this.getWorkingHours().add(wh);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getReligionId() {
        return religionId;
    }

    public void setReligionId(Integer religionId) {
        this.religionId = religionId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Integer getPobId() {
        return pobId;
    }

    public void setPobId(Integer pobId) {
        this.pobId = pobId;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getJobPositionId() {
        return jobPositionId;
    }

    public void setJobPositionId(String jobPositionId) {
        this.jobPositionId = jobPositionId;
    }

    public String getJobPositionName() {
        return jobPositionName;
    }

    public void setJobPositionName(String jobPositionName) {
        this.jobPositionName = jobPositionName;
    }

    public Integer getEducationLevelId() {
        return educationLevelId;
    }

    public void setEducationLevelId(Integer educationLevelId) {
        this.educationLevelId = educationLevelId;
    }

    public String getEducationLevelName() {
        return educationLevelName;
    }

    public void setEducationLevelName(String educationLevelName) {
        this.educationLevelName = educationLevelName;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPermanentRT() {
        return permanentRT;
    }

    public void setPermanentRT(String permanentRT) {
        this.permanentRT = permanentRT;
    }

    public String getPermanentRW() {
        return permanentRW;
    }

    public void setPermanentRW(String permanentRW) {
        this.permanentRW = permanentRW;
    }

    public Integer getPermanentSubdistrictId() {
        return permanentSubdistrictId;
    }

    public void setPermanentSubdistrictId(Integer permanentSubdistrictId) {
        this.permanentSubdistrictId = permanentSubdistrictId;
    }

    public String getPermanentSubdistrictName() {
        return permanentSubdistrictName;
    }

    public void setPermanentSubdistrictName(String permanentSubdistrictName) {
        this.permanentSubdistrictName = permanentSubdistrictName;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getResidentialRT() {
        return residentialRT;
    }

    public void setResidentialRT(String residentialRT) {
        this.residentialRT = residentialRT;
    }

    public String getResidentialRW() {
        return residentialRW;
    }

    public void setResidentialRW(String residentialRW) {
        this.residentialRW = residentialRW;
    }

    public Integer getResidentialSubdistrictId() {
        return residentialSubdistrictId;
    }

    public void setResidentialSubdistrictId(Integer residentialSubdistrictId) {
        this.residentialSubdistrictId = residentialSubdistrictId;
    }

    public String getResidentialSubdistrictName() {
        return residentialSubdistrictName;
    }

    public void setResidentialSubdistrictName(String residentialSubdistrictName) {
        this.residentialSubdistrictName = residentialSubdistrictName;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getReferalInstitutionId() {
        return referalInstitutionId;
    }

    public void setReferalInstitutionId(String referalInstitutionId) {
        this.referalInstitutionId = referalInstitutionId;
    }

    public String getReferalInstitutionName() {
        return referalInstitutionName;
    }

    public void setReferalInstitutionName(String referalInstitutionName) {
        this.referalInstitutionName = referalInstitutionName;
    }

    public Boolean getGuardian() {
        return isGuardian;
    }

    public void setGuardian(Boolean guardian) {
        isGuardian = guardian;
    }

    public Boolean getActive() {
        return active;
    }

    public Set<EmployeeFormalEducationVM> getFormalEducations() {
        return formalEducations;
    }

    public void setFormalEducations(Set<EmployeeFormalEducationVM> formalEducations) {
        this.formalEducations = formalEducations;
    }

    public Set<EmployeeFormalEducationVM> getUnorHistories() {
        return unorHistories;
    }

    public void setUnorHistories(Set<EmployeeFormalEducationVM> unorHistories) {
        this.unorHistories = unorHistories;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkingHourId() {
        return workingHourId;
    }

    public void setWorkingHourId(String workingHourId) {
        this.workingHourId = workingHourId;
    }

    public Boolean getWorkingShift() {
        return workingShift;
    }

    public void setWorkingShift(Boolean workingShift) {
        this.workingShift = workingShift;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Set<EmployeeWorkingHourVM> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Set<EmployeeWorkingHourVM> workingHours) {
        this.workingHours = workingHours;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Integer maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }
}
