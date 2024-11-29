package id.smartpesantren.dto;

import id.smartpesantren.constant.StudentStatus;
import id.smartpesantren.entity.Student;

import java.util.Date;

public class StudentDTO {
    private String id;
    private String nis;
    private String nisn;
    private String name;
    private String sex;
    private Date dob;
    private String joinYear;
    private String institution;
    private String category;
    private String status;

    public StudentDTO() {
    }

    public StudentDTO(Student s) {
        setId(s.getId());
        setName(s.getName());
        setDob(s.getDob());
        setNis(s.getNis());
        setNisn(s.getNisn());
        setSex(s.getSex().equalsIgnoreCase("M")? "Putra": "Putri");
        setJoinYear(s.getJoinYear().getCode());
        setInstitution(s.getInstitution().getName());
        setCategory(s.getCategory()== null? "": s.getCategory().getName());

        String status = "";
        if(s.getStatus() != null && s.getStatus() == StudentStatus.NOT_ACTIVE) {
            status = "Tidak Aktif";
        }
        if(s.getStatus() != null && s.getStatus() == StudentStatus.ACTIVE) {
            status = "Aktif";
        }
        if(s.getStatus() != null && s.getStatus() == StudentStatus.PASSED) {
            status = "Lulus";
        }
        if(s.getStatus() != null && s.getStatus() == StudentStatus.MUTATION) {
            status = "Pindah";
        }
        setStatus(status);
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getJoinYear() {
        return joinYear;
    }

    public void setJoinYear(String joinYear) {
        this.joinYear = joinYear;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
