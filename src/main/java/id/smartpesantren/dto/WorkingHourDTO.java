package id.smartpesantren.dto;

import id.smartpesantren.entity.AbstractAuditingEntity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class WorkingHourDTO extends AbstractAuditingEntity implements Serializable {
    private String id;
    private String code;
    private String name;
    private String description;
    private Set<WorkingHourDetailDTO> details = new HashSet<>();

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

    public Set<WorkingHourDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(Set<WorkingHourDetailDTO> details) {
        this.details = details;
    }
}
