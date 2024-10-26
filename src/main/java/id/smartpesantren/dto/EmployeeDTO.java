package id.smartpesantren.dto;

import id.smartpesantren.entity.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class EmployeeDTO {
    private String id;
    private String categoryId;
    private String categoryName;
    private String employeeNumber;
    private String name;
    private String sex;
    private String nik;
    private City pob;
    private Date dob;
    private String educationLevelId;
    private String educationLevelName;
    private String permanentAddress;
    private String permanentRT;
    private String permanentRW;
    private String permanentSubdistrictId;
    private String permanentSubdistrictName;

    private String residentialAddress; // Alamat domisili
    private String residentialRT;
    private String residentialRW;
    private String residentialSubdistrictId;
    private String residentialSubdistrictName;

    private String programStudy;
    private String faculty;
    private String institution;

    @Column(name = "is_employee", columnDefinition = "boolean default false")
    public Boolean isEmployee;

    @Column(name = "is_guardian", columnDefinition = "boolean default false")
    public Boolean isGuardian;
}
