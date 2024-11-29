package id.smartpesantren.dto;

import id.smartpesantren.entity.ClassRoomStudent;

public class ClassRoomStudentDTO {
    private String classRoomId;
    private String classRoomCode;
    private String classRoomName;
    private String id;
    private String nis;
    private String nisn;
    private String name;
    private String joinYear;

    public ClassRoomStudentDTO() {
    }

    public ClassRoomStudentDTO(ClassRoomStudent cs) {
        setId(cs.getId());
        setNis(cs.getStudent().getNis());
        setNisn(cs.getStudent().getNisn());
        setName(cs.getStudent().getName());
        setJoinYear(cs.getStudent().getJoinYear().getCode());

        setClassRoomId(cs.getClassRoom().getId());
        setClassRoomCode(cs.getClassRoom().getCode());
        setClassRoomName(cs.getClassRoom().getName());
    }

    public String getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(String classRoomId) {
        this.classRoomId = classRoomId;
    }

    public String getClassRoomCode() {
        return classRoomCode;
    }

    public void setClassRoomCode(String classRoomCode) {
        this.classRoomCode = classRoomCode;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
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

    public String getJoinYear() {
        return joinYear;
    }

    public void setJoinYear(String joinYear) {
        this.joinYear = joinYear;
    }
}
