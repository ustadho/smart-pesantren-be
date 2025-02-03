package id.smartpesantren.dto;

import id.smartpesantren.entity.JenisIzinSantri;

public class JenisIzinSantriDTO {
    private String id;
    private String code;
    private String name;
    private Integer maxDays;
    private String description;
    private Boolean active;

    public JenisIzinSantriDTO() {
    }

    public JenisIzinSantriDTO(JenisIzinSantri a) {
        this.id = a.getId();
        this.code = a.getCode();
        this.name = a.getName();
        this.description = a.getDescription();
        this.maxDays = a.getMaxDays();
        this.active = a.getActive();
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

    public Integer getMaxDays() {
        return maxDays;
    }

    public void setMaxDays(Integer maxDays) {
        this.maxDays = maxDays;
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
