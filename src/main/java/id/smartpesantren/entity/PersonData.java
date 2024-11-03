package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "person_data")
public class PersonData extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    @ManyToOne
    @JoinColumn(name = "employee_category_id")
    EmployeeCategory employeeCategory;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonIgnore
    PersonData manager;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    Organization organization;

    @ManyToOne
    @JoinColumn(name = "section_id")
    Section section;

    @ManyToOne
    @JoinColumn(name = "job_position_id")
    JobPosition jobPosition;

    @ManyToOne
    @JoinColumn(name = "employee_status_id")
    EmployeeStatus employeeStatus;

    @Temporal(TemporalType.DATE)
    private Date joinDate;

    @Column(length = 50)
    private String employeeNo;

    @Column(nullable = false)
    private String name;

    //M: Male, F: Female
    @Column(nullable = false)
    private String sex;

    private String nik;

    // Place of Birth

    @ManyToOne
    @JoinColumn(name = "pob_city_id")
    private City pob;

    // Date of Birth
    @Temporal(TemporalType.DATE)
    private Date dob;

    @ManyToOne
    @JoinColumn(name = "marital_status_id")
    private MaritalStatus maritalStatus;

    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "education_level_id")
    private EducationLevel educationLevel; //Pendidikan terakhir

    private String permanentAddress;

    @Column(name = "permanent_rt")
    private String permanentRT;

    @Column(name = "permanent_rw")
    private String permanentRW;

    @ManyToOne
    @JoinColumn(name = "permanent_subdistrict_id")
    private SubDistrict permanentSubDistrict;

    private String residentialAddress; // Alamat domisili

    @Column(name = "residential_rt")
    private String residentialRT;

    @Column(name = "residential_rw")
    private String residentialRW;

    @ManyToOne
    @JoinColumn(name = "residental_subdistrict_id")
    private SubDistrict residentalSubDistrict;

    @Column(name = "majors")
    private String majors;

    private String faculty;

    @ManyToOne
    @JoinColumn(name = "referal_institution_id")
    private ReferalInstitution referalInstitution;

    @Column(name = "is_employee", columnDefinition = "boolean default false")
    public Boolean isEmployee;

    @Column(name = "is_guardian", columnDefinition = "boolean default false")
    public Boolean isGuardian;  // Walisantri

    @Column(length = 10, nullable = false)
    private String personType; // Isi saja dengan 'EMPLOYEE', 'PARENT', 'STUDENT'. ini memang agak ambigu dengan adanya `isEmployee`

    @Column(columnDefinition = "boolean default true")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "working_hour_id")
    private WorkingHour workingHour;

    @OneToMany(mappedBy = "person", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<EmployeeFormalEducaton> formalEducations = new HashSet<>();

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<EmployeeWorkingHour> workingHours = new HashSet<>();

    public PersonData() {
    }

    public PersonData(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonData getManager() {
        return manager;
    }

    public void setManager(PersonData manager) {
        this.manager = manager;
    }

    public Set<EmployeeFormalEducaton> getFormalEducations() {
        return formalEducations;
    }

    public void setFormalEducations(Set<EmployeeFormalEducaton> formalEducations) {
        this.formalEducations = formalEducations;
    }

    public Set<EmployeeWorkingHour> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Set<EmployeeWorkingHour> workingHours) {
        this.workingHours = workingHours;
    }

    public Foundation getFoundation() {
        return foundation;
    }

    public void setFoundation(Foundation foundation) {
        this.foundation = foundation;
    }

    public EmployeeCategory getEmployeeCategory() {
        return employeeCategory;
    }

    public void setEmployeeCategory(EmployeeCategory employeeCategory) {
        this.employeeCategory = employeeCategory;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public JobPosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
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

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
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

    public SubDistrict getPermanentSubDistrict() {
        return permanentSubDistrict;
    }

    public void setPermanentSubDistrict(SubDistrict permanentSubDistrict) {
        this.permanentSubDistrict = permanentSubDistrict;
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

    public SubDistrict getResidentalSubDistrict() {
        return residentalSubDistrict;
    }

    public void setResidentalSubDistrict(SubDistrict residentalSubDistrict) {
        this.residentalSubDistrict = residentalSubDistrict;
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

    public ReferalInstitution getReferalInstitution() {
        return referalInstitution;
    }

    public void setReferalInstitution(ReferalInstitution referalInstitution) {
        this.referalInstitution = referalInstitution;
    }

    public Boolean isEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(Boolean employee) {
        isEmployee = employee;
    }

    public Boolean isGuardian() {
        return isGuardian;
    }

    public void setIsGuardian(Boolean guardian) {
        isGuardian = guardian;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public Boolean getEmployee() {
        return isEmployee;
    }

    public void setEmployee(Boolean employee) {
        isEmployee = employee;
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

    public WorkingHour getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(WorkingHour workingHour) {
        this.workingHour = workingHour;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

}
