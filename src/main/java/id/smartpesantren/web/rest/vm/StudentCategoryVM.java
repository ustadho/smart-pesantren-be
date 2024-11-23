package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.StudentCategory;

import javax.validation.constraints.NotNull;

public class StudentCategoryVM {
    private String id;

    @NotNull
    private String name;
    private String description;
    private Boolean isDefault;

    public StudentCategoryVM() {
    }

    public StudentCategoryVM(StudentCategory s) {
        setId(s.getId());
        setName(s.getName());
        setDescription(s.getDescription());
        setDefault(s.getDefault());
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

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}
