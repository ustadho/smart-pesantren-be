package id.smartpesantren.dto;

import id.smartpesantren.entity.Organization;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OrganizationDTO {
    private String id;
    private String parentId;

    @NotNull @NotEmpty
    private String code;

    @NotNull @NotEmpty
    private String name;
    private String description;
    private Boolean active;

    @NotNull @NotEmpty
    private String jobLevelId;

    public OrganizationDTO() {
    }

    public OrganizationDTO(Organization o) {
        this(o.getId(),
                o.getParent() == null? null: o.getParent().getId(),
                o.getCode(),
                o.getName(),
                o.getDescription(),
                o.getActive(),
                o.getJobLevel().getId()
                );
    }

    public OrganizationDTO(String id, String parentId, String code, String name, String description, Boolean active, String jobLvelId) {
        this.id = id;
        this.parentId = parentId;
        this.code = code;
        this.name = name;
        this.description = description;
        this.active = active;
        this.jobLevelId = jobLvelId;
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

    public String getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(String jobLevelId) {
        this.jobLevelId = jobLevelId;
    }
}
