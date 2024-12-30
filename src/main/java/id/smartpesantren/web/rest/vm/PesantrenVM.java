package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.Pesantren;

import javax.validation.constraints.NotNull;

public class PesantrenVM {
    private String id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    private String description;

    private String sex;

    private Boolean active;

    public PesantrenVM() {
    }

    public PesantrenVM(Pesantren a) {
        setId(a.getId());
        setName(a.getName());
        setCode(a.getCode());
        setDescription(a.getDescription());
        setSex(a.getSex());
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
