package id.smartpesantren.dto;

import id.smartpesantren.entity.Curriculum;
import id.smartpesantren.entity.JobLevel;

public class CurriculumDTO {
    private String id;
    private String code;
    private String name;
    private String description;
    private Integer startYear;
    private Boolean active;

    public CurriculumDTO() {
    }

    public CurriculumDTO(Curriculum a) {
        setId(a.getId());
        setCode(a.getCode());
        setName(a.getName());
        setDescription(a.getDescription());
        setStartYear(a.getStartYear());
        setActive(a.getActive());
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

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
