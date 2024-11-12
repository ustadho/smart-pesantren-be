package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.Institution;

import javax.validation.constraints.NotNull;

public class InstitutionVM {
    private String id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    private String description;

    private Integer levelId;

    private Boolean active;

    public InstitutionVM() {
    }

    public InstitutionVM(Institution a) {
        setId(a.getId());
        setName(a.getName());
        setCode(a.getCode());
        setDescription(a.getDescription());
        setLevelId(a.getLevel().getId());
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

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
