package id.smartpesantren.dto;

import id.smartpesantren.entity.ClassRoom;

public class ClassRoomDTO {
    private String id;
    private String academicYearId;
    private String academicYearCode;
    private String institusi;
    private String educationLevel;
    private Short classLevel;
    private String code;
    private String name;
    private String sex;
    private String sexLabel;
    private Short capacity;
    private String description;
    private String room;
    private String location;

    public ClassRoomDTO() {
    }

    public ClassRoomDTO(ClassRoom c) {
        setId(c.getId());
        setCode(c.getCode());
        setName(c.getName());
        setAcademicYearId(c.getAcademicYear().getId());
        setAcademicYearCode(c.getAcademicYear().getCode());
        setInstitusi(c.getInstitution().getName());
        setEducationLevel(c.getClassLevel().getEducationLevel().getName());
        setClassLevel(c.getClassLevel().getLevel());
        setRoom(c.getRoom());
        setLocation(c.getLocation().getName());
        setDescription(c.getDescription());
        setCapacity(c.getCapacity());
        setSex(c.getSex());
        setSexLabel(c.getSex() == null? "": (c.getSex().equalsIgnoreCase("M")? "Putra": "Putri"));
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(String academicYearId) {
        this.academicYearId = academicYearId;
    }

    public String getAcademicYearCode() {
        return academicYearCode;
    }

    public void setAcademicYearCode(String academicYearCode) {
        this.academicYearCode = academicYearCode;
    }

    public String getInstitusi() {
        return institusi;
    }

    public void setInstitusi(String institusi) {
        this.institusi = institusi;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Short getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(Short classLevel) {
        this.classLevel = classLevel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSexLabel() {
        return sexLabel;
    }

    public void setSexLabel(String sexLabel) {
        this.sexLabel = sexLabel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getCapacity() {
        return capacity;
    }

    public void setCapacity(Short capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
