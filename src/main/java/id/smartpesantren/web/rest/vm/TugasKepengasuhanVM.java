package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.TugasKepengasuhan;

import javax.validation.constraints.NotNull;

public class TugasKepengasuhanVM {
    private String id;

    private String code;

    @NotNull
    private String name;

    private String description;

    private Boolean active;

    public TugasKepengasuhanVM() {
    }

    public TugasKepengasuhanVM(TugasKepengasuhan a) {
        setId(a.getId());
        setCode(a.getCode());
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
