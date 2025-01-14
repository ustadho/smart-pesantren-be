package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import id.smartpesantren.web.EmployementType;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
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

    @ManyToOne
    @JoinColumn(name = "religion_id")
    private Religion religion;

    private String permanentAddress;

    @Column(name = "permanent_rt")
    private String permanentRT;

    @Column(name = "permanent_rw")
    private String permanentRW;

    @Column(name = "permanent_postal_code")
    private String permanentPostalCode;

    @ManyToOne
    @JoinColumn(name = "permanent_subdistrict_id")
    private SubDistrict permanentSubDistrict;

    private String residentialAddress; // Alamat domisili

    @Column(name = "residential_rt")
    private String residentialRT;

    @Column(name = "residential_rw")
    private String residentialRW;

    @ManyToOne
    @JoinColumn(name = "residential_subdistrict_id")
    private SubDistrict residentialSubDistrict;

    @Column(name = "residential_postal_code")
    private String residentialPostalCode;

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

    @Column(columnDefinition = "boolean default false")
    private Boolean workingShift;

    @ManyToOne
    @JoinColumn(name = "nationality_id")
    private Country nationality;

    private String monthlyRevenue;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private PersonTitle title; // Bapak, Ibu, Sdr., Sdri., Alm. Almh.

    @ManyToOne
    @JoinColumn(name = "employement_type_id")
    EmployementType employementType;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "hr_employee_institution",
            joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "institution_id", referencedColumnName = "id")})
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 20)
    private Set<Institution> institutions = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "hr_employee_pesantren",
            joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "pesantren_id", referencedColumnName = "id")})
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 20)
    private Set<Institution> pesantrens = new HashSet<>();

    private String photo;

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

    public Boolean getWorkingShift() {
        return workingShift;
    }

    public void setWorkingShift(Boolean workingShift) {
        this.workingShift = workingShift;
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

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
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

    public String getPermanentPostalCode() {
        return permanentPostalCode;
    }

    public void setPermanentPostalCode(String permanentPostalCode) {
        this.permanentPostalCode = permanentPostalCode;
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

    public SubDistrict getResidentialSubDistrict() {
        return residentialSubDistrict;
    }

    public void setResidentialSubDistrict(SubDistrict residentialSubDistrict) {
        this.residentialSubDistrict = residentialSubDistrict;
    }

    public String getResidentialPostalCode() {
        return residentialPostalCode;
    }

    public void setResidentialPostalCode(String residentialPostalCode) {
        this.residentialPostalCode = residentialPostalCode;
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

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public String getMonthlyRevenue() {
        return monthlyRevenue;
    }

    public void setMonthlyRevenue(String monthlyRevenue) {
        this.monthlyRevenue = monthlyRevenue;
    }

    public PersonTitle getTitle() {
        return title;
    }

    public void setTitle(PersonTitle title) {
        this.title = title;
    }

    public EmployementType getEmployementType() {
        return employementType;
    }

    public void setEmployementType(EmployementType employementType) {
        this.employementType = employementType;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Set<Institution> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(Set<Institution> institutions) {
        this.institutions = institutions;
    }

    public Set<Institution> getPesantrens() {
        return pesantrens;
    }

    public void setPesantrens(Set<Institution> pesantrens) {
        this.pesantrens = pesantrens;
    }
}
