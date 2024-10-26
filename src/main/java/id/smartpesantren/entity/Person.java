package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class Person extends AbstractAuditingEntity implements Serializable {
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

    private String employeeNumber;

    @Column(nullable = false)
    private String name;

    //M: Male, F: Female
    private String sex;

    private String nik;

    // Place of Birth
    private City pob;

    // Date of Birth
    private Date dob;

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

    @Column(name = "program_study")
    private String programStudy;

    private String faculty;

    private String institution;

    @Column(name = "is_employee", columnDefinition = "boolean default false")
    public Boolean isEmployee;

    @Column(name = "is_guardian", columnDefinition = "boolean default false")
    public Boolean isGuardian;  // Walisantri

    @Column(length = 4, nullable = false)
    private String personType; // Isi saja dengan 'EMPLOYEE', 'PARENT', 'STUDENT'. ini memang agak ambigu dengan adanya `isEmployee`
}
