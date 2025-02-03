package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.JenisKegiatan;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class JenisKegiatanVM {
    private String id;

    private String code;

    @NotNull
    private String name;

    private String description;

    private Date inTime;

    private Boolean active;

    public JenisKegiatanVM() {
    }

    public JenisKegiatanVM(JenisKegiatan a) {
        setId(a.getId());
        setCode(a.getCode());
        setName(a.getName());
        setDescription(a.getDescription());
        setInTime(a.getInTime());
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

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
