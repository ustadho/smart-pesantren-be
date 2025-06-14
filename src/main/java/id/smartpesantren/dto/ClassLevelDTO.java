package id.smartpesantren.dto;

import id.smartpesantren.entity.ClassLevel;

public class ClassLevelDTO {
    private String id;
    private String code;
    private String name;
    private Short level;
    private String description;
    private String educationLevel;
    private String color;

    public ClassLevelDTO() {
    }

    public ClassLevelDTO(ClassLevel c) {
        setId(c.getId());
        setCode(c.getCode());
        setName(c.getName());
        setLevel(c.getLevel());
        setDescription(c.getDescription());
        setEducationLevel(c.getEducationLevel().getName());
        setColor(c.getColor());
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

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
