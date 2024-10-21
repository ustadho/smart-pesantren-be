package id.smartpesantren.dto;

import id.smartpesantren.entity.JobLevel;

public class JobLevelDTO {
    private String id;
    private String code;
    private String name;
    private String description;
    private String color;
    private Integer level;

    public JobLevelDTO() {
    }

    public JobLevelDTO(JobLevel l) {
        this(l.getId(), l.getCode(), l.getName(), l.getDescription(), l.getLevel(), l.getColor());
    }
    public JobLevelDTO(String id, String code, String name, String description, Integer level, String color) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.level = level;
        this.color = color;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
