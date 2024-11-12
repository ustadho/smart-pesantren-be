package id.smartpesantren.dto;

import id.smartpesantren.entity.Subject;

public class SubjectDTO {
    private String id;
    private String code;
    private String name;
    private String description;
    private String category;
    private Boolean active;

    public SubjectDTO() {
    }

    public SubjectDTO(Subject a) {
        this(a.getId(), a.getCode(), a.getName(), a.getDescription(), a.getCategory().getName(), a.getActive());
    }
    public SubjectDTO(String id, String code, String name, String description, String category, Boolean active) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
