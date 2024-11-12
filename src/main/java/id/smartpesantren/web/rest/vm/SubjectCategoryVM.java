package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.SubjectCategory;

import javax.validation.constraints.NotNull;

public class SubjectCategoryVM {
    private String id;

    @NotNull
    private String name;

    private String description;

    private Boolean active;

    public SubjectCategoryVM() {
    }

    public SubjectCategoryVM(SubjectCategory a) {
        setId(a.getId());
        setName(a.getName());
        setDescription(a.getDescription());
        setActive(a.getActive());
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
