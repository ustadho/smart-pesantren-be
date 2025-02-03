package id.smartpesantren.dto;

import id.smartpesantren.entity.Student;

import java.util.Date;

public class SantriListDTO {
    private String id;
    private String nis;
    private String nisn;
    private String name;
    private String pob;
    private Date dob;
    private String fathersName;

    public SantriListDTO() {
    }

    public SantriListDTO(Student s) {
        setId(s.getId());
        setNis(s.getNis());
        setNisn(s.getNisn());
        setName(s.getName());
        setPob(s.getPob() == null? null: s.getPob().getName());
        setDob(s.getDob());
        setFathersName(s.getFather() == null? null: s.getFather().getName());
    }

    public SantriListDTO(String id, String nis, String nisn, String name, String pob, Date dob, String fathersName) {
        this.id = id;
        this.nis = nis;
        this.nisn = nisn;
        this.name = name;
        this.pob = pob;
        this.dob = dob;
        this.fathersName = fathersName;
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

    public String getPob() {
        return pob;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }
}
