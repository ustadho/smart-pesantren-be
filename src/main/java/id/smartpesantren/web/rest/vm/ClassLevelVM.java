package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.ClassLevel;

import javax.validation.constraints.NotNull;

public class ClassLevelVM {
    private String id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    @NotNull
    private Short level;
    private String description;
    private String color;

    @NotNull
    private Integer educationLevelId;

    public ClassLevelVM() {
    }

    public ClassLevelVM(ClassLevel c) {
        setId(c.getId());
        setCode(c.getCode());
        setName(c.getName());
        setDescription(c.getDescription());
        setColor(c.getColor());
        setEducationLevelId(c.getEducationLevel().getId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getEducationLevelId() {
        return educationLevelId;
    }

    public void setEducationLevelId(Integer educationLevelId) {
        this.educationLevelId = educationLevelId;
    }
}
