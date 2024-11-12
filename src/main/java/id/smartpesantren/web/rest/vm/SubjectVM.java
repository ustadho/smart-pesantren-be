package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.Subject;

import javax.validation.constraints.NotNull;

public class SubjectVM {
    private String id;

    private String code;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String categoryId;

    private Boolean active;

    public SubjectVM() {
    }

    public SubjectVM(Subject a) {
        setId(a.getId());
        setCode(a.getCode());
        setName(a.getName());
        setDescription(a.getDescription());
        setCategoryId(a.getCategory().getId());
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
