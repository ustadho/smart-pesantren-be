package id.smartpesantren.dto;

import id.smartpesantren.entity.JenisPelanggaran;
public class JenisPelanggaranDTO {
    private String id;
    private String code;
    private String name;
    private String description;
    private Boolean active;

    public JenisPelanggaranDTO() {
    }

    public JenisPelanggaranDTO(JenisPelanggaran a) {
        this(a.getId(), a.getCode(), a.getName(), a.getDescription(), a.getActive());
    }
    public JenisPelanggaranDTO(String id, String code, String name, String description, Boolean active) {
        this.id = id;
        this.code = code;
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
