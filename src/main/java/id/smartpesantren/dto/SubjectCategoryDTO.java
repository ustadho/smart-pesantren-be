package id.smartpesantren.dto;

import id.smartpesantren.entity.SubjectCategory;

public class SubjectCategoryDTO {
    private String id;
    private String name;
    private String description;
    private Boolean active;

    public SubjectCategoryDTO() {
    }

    public SubjectCategoryDTO(SubjectCategory a) {
        this(a.getId(), a.getName(), a.getDescription(), a.getActive());
    }
    public SubjectCategoryDTO(String id, String name, String description, Boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
