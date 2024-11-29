package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.Student;

public class ClassRoomStudentVMDetail {
    public String id;
    public String studentId;
    private String nis;
    private String nisn;
    private String name;
    private String joinYear;

    public ClassRoomStudentVMDetail() {
    }

    public ClassRoomStudentVMDetail(String id, Student s) {
        setId(id);
        setStudentId(s.getId());
        setNis(s.getNis());
        setNisn(s.getNisn());
        setName(s.getName());
        setJoinYear(s.getJoinYear().getCode());
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getJoinYear() {
        return joinYear;
    }

    public void setJoinYear(String joinYear) {
        this.joinYear = joinYear;
    }
}
