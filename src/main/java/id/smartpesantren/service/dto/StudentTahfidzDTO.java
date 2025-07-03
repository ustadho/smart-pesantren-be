package id.smartpesantren.service.dto;

import id.smartpesantren.entity.TahfidzKonversi;

public class StudentTahfidzDTO {
    private String studentId;
    private String studentName;
    private String studentNis;
    private String studentNisn;
    private String studentGender;
    private String joinYear;
    private String photo;

    private TahfidzKonversi tahfidzTarget;
    private TahfidzKonversi tahfidzCapaian;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNis() {
        return studentNis;
    }

    public void setStudentNis(String studentNis) {
        this.studentNis = studentNis;
    }

    public String getStudentNisn() {
        return studentNisn;
    }

    public void setStudentNisn(String studentNisn) {
        this.studentNisn = studentNisn;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getJoinYear() {
        return joinYear;
    }

    public void setJoinYear(String joinYear) {
        this.joinYear = joinYear;
    }

    public TahfidzKonversi getTahfidzTarget() {
        return tahfidzTarget;
    }

    public void setTahfidzTarget(TahfidzKonversi tahfidzTarget) {
        this.tahfidzTarget = tahfidzTarget;
    }

    public TahfidzKonversi getTahfidzCapaian() {
        return tahfidzCapaian;
    }

    public void setTahfidzCapaian(TahfidzKonversi tahfidzCapaian) {
        this.tahfidzCapaian = tahfidzCapaian;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
