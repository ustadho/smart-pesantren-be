package id.smartpesantren.dto;

public class ClassRoomDTO {
    private String id;
    private String academicYearId;
    private String academicYearCode;
    private String levelId;
    private Short level;
    private String code;
    private String name;
    private Short capacity;
    private String description;

    public ClassRoomDTO() {
    }

    public ClassRoomDTO(String id, String academicYearId, String academicYearCode, String levelId, Short level, String code, String name, Short capacity, String description) {
        this.id = id;
        this.academicYearId = academicYearId;
        this.academicYearCode = academicYearCode;
        this.levelId = levelId;
        this.level = level;
        this.code = code;
        this.name = name;
        this.capacity = capacity;
        this.description = description;
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

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
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
}
