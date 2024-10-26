package id.smartpesantren.dto;

import id.smartpesantren.entity.ReferalInstitution;

public class ReferalInstitutionDTO {
    private String id;
    private String name;
    private String description;

    public ReferalInstitutionDTO() {
    }

    public ReferalInstitutionDTO(ReferalInstitution r) {
        this(r.getId(), r.getName(), r.getDescription());
    }

    public ReferalInstitutionDTO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}
