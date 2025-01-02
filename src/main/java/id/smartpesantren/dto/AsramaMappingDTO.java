package id.smartpesantren.dto;

import id.smartpesantren.entity.AsramaMapping;

public class AsramaMappingDTO {
    private String id;
    private String academicYearId;
    private String academicYearCode;
    private String asramaId;
    private String asramaName;
    private String buildingName;
    private String pesantren;
    private String sex;
    private String sexLabel;
    private Integer capacity;
    private Integer studentCount;

    public AsramaMappingDTO() {
    }

    public AsramaMappingDTO(AsramaMapping a) {
        setId(a.getId());
        setAcademicYearId(a.getAcademicYear().getId());
        setAcademicYearCode(a.getAcademicYear().getCode());
        setAsramaId(a.getAsrama().getId());
        setAsramaName(a.getAsrama().getName());
        setBuildingName(a.getAsrama().getBuilding().getName());
        setPesantren(a.getAsrama().getPesantren().getName());
        setCapacity(a.getAsrama().getCapacity().intValue());
        setSex(a.getAsrama().getPesantren().getSex());
        setSexLabel(a.getAsrama().getPesantren().getSex() == null? "": (a.getAsrama().getPesantren().getSex().equalsIgnoreCase("M")? "Putra": "Putri"));
        setStudentCount(a.getStudents()==null? 0: a.getStudents().size());
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

    public String getAsramaId() {
        return asramaId;
    }

    public void setAsramaId(String asramaId) {
        this.asramaId = asramaId;
    }

    public String getAsramaName() {
        return asramaName;
    }

    public void setAsramaName(String asramaName) {
        this.asramaName = asramaName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getPesantren() {
        return pesantren;
    }

    public void setPesantren(String pesantren) {
        this.pesantren = pesantren;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }
}
