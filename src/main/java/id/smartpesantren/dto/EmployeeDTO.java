package id.smartpesantren.dto;

import id.smartpesantren.entity.City;
import id.smartpesantren.entity.PersonData;

import java.util.Date;

public class EmployeeDTO {
    private String id;
    private String categoryId;
    private String categoryName;
    private String employeeNo;
    private String name;
    private String sex;
    private String nik;
    private City pob;
    private Date dob;
    private String organizationId;
    private String organizationName;
    private String sectionId;
    private String sectionName;
    private String jobPositionId;
    private String jobPositionName;
    private Integer educationLevelId;
    private String educationLevelName;
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
    private String institutionId;
    private String institutionName;

    public Boolean isGuardian;

    public Boolean active;

    public EmployeeDTO() {
    }

    public EmployeeDTO(PersonData p) {
        this(p.getId(),
                p.getEmployeeCategory().getId(),
                p.getEmployeeCategory().getName(),
                p.getEmployeeNo(),
                p.getName(),
                p.getSex(),
                p.getNik(),
                p.getPob(),
                p.getDob(),
                p.getOrganization() == null? null: p.getOrganization().getId(),
                p.getOrganization() == null? null: p.getOrganization().getName(),
                p.getSection()==null? null: p.getSection().getId(),
                p.getSection()==null? null: p.getSection().getName(),
                p.getJobPosition() == null? null: p.getJobPosition().getId(),
                p.getJobPosition() == null? null: p.getJobPosition().getName(),
                p.getEducationLevel() == null? null: p.getEducationLevel().getId(),
                p.getEducationLevel() == null? null: p.getEducationLevel().getName(),
                p.getPermanentAddress(),
                p.getPermanentRT(),
                p.getPermanentRW(),
                p.getPermanentSubDistrict() == null? null: p.getPermanentSubDistrict().getId(),
                p.getPermanentSubDistrict() == null? null: p.getPermanentSubDistrict().getName(),
                p.getResidentialAddress(),
                p.getResidentialRT(),
                p.getResidentialRW(),
                p.getResidentalSubDistrict() == null? null: p.getResidentalSubDistrict().getId(),
                p.getResidentalSubDistrict() == null? null: p.getResidentalSubDistrict().getName(),
                p.getMajors(),
                p.getFaculty(),
                p.getReferalInstitution() == null? null: p.getReferalInstitution().getId(),
                p.getReferalInstitution() == null? null: p.getReferalInstitution().getName(),
                p.isGuardian(),
                p.getActive()
            );
    }

    public EmployeeDTO(String id, String categoryId, String categoryName, String employeeNo, String name, String sex,
                       String nik, City pob, Date dob, String organizationId, String organizationName,
                       String sectionId, String sectionName, String jobPositionId, String jobPositionName,
                       Integer educationLevelId, String educationLevelName, String permanentAddress, String permanentRT, String permanentRW, Integer permanentSubdistrictId, String permanentSubdistrictName, String residentialAddress, String residentialRT, String residentialRW, Integer residentialSubdistrictId, String residentialSubdistrictName, String majors, String faculty,
                       String institutionId, String istitutionName, Boolean isGuardian, Boolean active) {
        this.id = id;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.employeeNo = employeeNo;
        this.name = name;
        this.sex = sex;
        this.nik = nik;
        this.pob = pob;
        this.dob = dob;
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.jobPositionId = jobPositionId;
        this.jobPositionName = jobPositionName;
        this.educationLevelId = educationLevelId;
        this.educationLevelName = educationLevelName;
        this.permanentAddress = permanentAddress;
        this.permanentRT = permanentRT;
        this.permanentRW = permanentRW;
        this.permanentSubdistrictId = permanentSubdistrictId;
        this.permanentSubdistrictName = permanentSubdistrictName;
        this.residentialAddress = residentialAddress;
        this.residentialRT = residentialRT;
        this.residentialRW = residentialRW;
        this.residentialSubdistrictId = residentialSubdistrictId;
        this.residentialSubdistrictName = residentialSubdistrictName;
        this.majors = majors;
        this.faculty = faculty;
        this.institutionId = institutionId;
        this.institutionName = institutionName;
        this.isGuardian = isGuardian;
        this.active = active;
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

    public City getPob() {
        return pob;
    }

    public void setPob(City pob) {
        this.pob = pob;
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

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
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

    public void setActive(Boolean active) {
        this.active = active;
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
}
