package id.smartpesantren.web.rest.dto;

import java.time.Instant;

public class PeriodEndDTO {
    private String id;
    private String name;
    private String description;
    private Instant createdDate;
    private String createdBy;

    public PeriodEndDTO() {
    }

    public PeriodEndDTO(String id, String name, String description, Instant createdDate, String createdBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
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

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
