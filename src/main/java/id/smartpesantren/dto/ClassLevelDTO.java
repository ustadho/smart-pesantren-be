package id.smartpesantren.dto;

public class ClassLevelDTO {
    private String id;
    private Short level;
    private String description;

    public ClassLevelDTO() {
    }

    public ClassLevelDTO(String id, Short level, String description) {
        this.id = id;
        this.level = level;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
