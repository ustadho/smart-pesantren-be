package id.smartpesantren.dto;

import id.smartpesantren.entity.JobPosition;
import id.smartpesantren.entity.Organization;

public class JobPositionDTO {
    private String id;
    private String code;
    private String name;
    private String description;
    private String jobLevelId;
    private String jobLevelName;
    private Integer level;
    private Boolean active;

    public JobPositionDTO() {
    }

    public JobPositionDTO(JobPosition o) {
        this(o.getId(),
            o.getCode(),
            o.getName(),
            o.getDescription(),
            o.getJobLevel().getId(),
            o.getJobLevel().getName(),
            o.getJobLevel().getLevel(),
            o.getActive()
        );
    }

    public JobPositionDTO(String id, String code, String name, String description, String jobLevelId, String jobLevelName, Integer level, Boolean active) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.active = active;
        this.jobLevelId = jobLevelId;
        this.jobLevelName = jobLevelName;
        this.level = level;
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

    public String getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(String jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public String getJobLevelName() {
        return jobLevelName;
    }

    public void setJobLevelName(String jobLevelName) {
        this.jobLevelName = jobLevelName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
