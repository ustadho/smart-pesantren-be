package id.smartpesantren.dto;

import id.smartpesantren.entity.AcademicActivityTime;

import java.util.Date;

public class AcademicActivityTimeDTO {
    private String id;
    private String institutionId;
    private String institutionName;
    private Integer seq;
    private Date startTime;
    private Date endTime;
    private String description;

    public AcademicActivityTimeDTO() {
    }

    public AcademicActivityTimeDTO(AcademicActivityTime t) {
        setId(t.getId());
        setInstitutionId(t.getInstitution().getId());
        setInstitutionName(t.getInstitution().getName());
        setSeq(t.getSeq());
        setStartTime(t.getStartTime());
        setEndTime(t.getEndTime());
        setDescription(t.getDescription());
    }

    public AcademicActivityTimeDTO(String id, String institutionId, String institutionName, Integer seq, Date startTime, Date endTime, String description) {
        this.id = id;
        this.institutionId = institutionId;
        this.institutionName = institutionName;
        this.seq = seq;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
