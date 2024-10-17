package id.smartpesantren.dto;

import id.smartpesantren.entity.Organization;

public class OrganizationDTO {
    private String id;
    private String parentId;
    private String code;
    private String name;
    private String description;
    private Boolean active;

    public OrganizationDTO() {
    }

    public OrganizationDTO(Organization o) {
        this(o.getId(),
                o.getParent() == null? null: o.getParent().getId(),
                o.getCode(),
                o.getName(),
                o.getDescription(),
                o.getActive());
    }

    public OrganizationDTO(String id, String parentId, String code, String name, String description, Boolean active) {
        this.id = id;
        this.parentId = parentId;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
