package id.smartpesantren.dto;


import id.smartpesantren.entity.Halaqoh;

public class HalaqohDTO {
    private String id;
    private String academicYearId;
    private String academicYearCode;
    private String pesantrenId;
    private String pesantrenName;
    private Integer studentCount;

    public HalaqohDTO() {
    }

    public HalaqohDTO(Halaqoh a) {
        setId(a.getId());
        setAcademicYearId(a.getAcademicYear().getId());
        setAcademicYearCode(a.getAcademicYear().getCode());
        setPesantrenId(a.getPesantren().getId());
        setPesantrenName(a.getPesantren().getName());
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

    public String getPesantrenName() {
        return pesantrenName;
    }

    public void setPesantrenName(String pesantrenName) {
        this.pesantrenName = pesantrenName;
    }

    public String getPesantrenId() {
        return pesantrenId;
    }

    public void setPesantrenId(String pesantrenId) {
        this.pesantrenId = pesantrenId;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }
}
