package id.smartpesantren.dto;

import id.smartpesantren.entity.JenisKegiatan;

import java.util.Date;

public class JenisKegiatanDTO {
    private String id;
    private String code;
    private String name;
    private String description;
    private Date inTime;
    private Boolean active;

    public JenisKegiatanDTO() {
    }

    public JenisKegiatanDTO(JenisKegiatan a) {
        this(a.getId(), a.getCode(), a.getName(), a.getDescription(), a.getInTime(), a.getActive());
    }
    public JenisKegiatanDTO(String id, String code, String name, String description, Date inTime, Boolean active) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.inTime = inTime;
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

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
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
