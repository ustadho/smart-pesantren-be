package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.ClassRoom;

import javax.validation.constraints.NotNull;

public class ClassRoomVM {
    private String id;
    @NotNull
    private String academicYearId;
    @NotNull
    private String institutionId;
    @NotNull
    private String classLevelId;
    private String code;
    @NotNull
    private String name;

    private String room;

    @NotNull
    private String sex;
    @NotNull
    private Short capacity;
    private String description;
    private String locationId;
    private String homeTeacherId;
    private String curriculumId;

    public ClassRoomVM() {
    }

    public ClassRoomVM(ClassRoom c) {
        setId(c.getId());
        setInstitutionId(c.getInstitution().getId());
        setAcademicYearId(c.getAcademicYear().getId());
        setClassLevelId(c.getClassLevel().getId());
        setCode(c.getCode());
        setName(c.getName());
        setRoom(c.getRoom());
        setSex(c.getSex());
        setCapacity(c.getCapacity());
        setDescription(c.getDescription());
        setCurriculumId(c.getCurriculum().getId());
        setLocationId(c.getLocation().getId());
        setHomeTeacherId(c.getHomeRoomTeacher().getId());
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

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getClassLevelId() {
        return classLevelId;
    }

    public void setClassLevelId(String classLevelId) {
        this.classLevelId = classLevelId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getHomeTeacherId() {
        return homeTeacherId;
    }

    public void setHomeTeacherId(String homeTeacherId) {
        this.homeTeacherId = homeTeacherId;
    }

    public String getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(String curriculumId) {
        this.curriculumId = curriculumId;
    }
}
