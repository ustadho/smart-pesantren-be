package id.smartpesantren.dto;

import id.smartpesantren.entity.Student;

import java.util.Date;

public class StudentDTO {
    private String id;
    private String nis;
    private String nisn;
    private String name;
    private Date dob;

    public StudentDTO() {
    }

    public StudentDTO(Student s) {
        setId(s.getId());
        setName(s.getName());
        setDob(s.getDob());
        setNis(s.getNis());
        setNisn(s.getNisn());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
