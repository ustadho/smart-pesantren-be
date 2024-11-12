package id.smartpesantren.dto;

import id.smartpesantren.entity.Institution;

public class InstitutionDTO {
    private String id;
    private String code;
    private String name;
    private String description;
    private String level;
    private Boolean active;

    public InstitutionDTO() {
    }

    public InstitutionDTO(Institution a) {
        this(a.getId(), a.getCode(), a.getName(), a.getDescription(), a.getLevel().getName(), a.getActive());
    }
    public InstitutionDTO(String id, String code, String name, String description, String level, Boolean active) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.level = level;
        this.active = active;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
