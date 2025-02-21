package id.smartpesantren.dto;

import id.smartpesantren.entity.AcademicActivityTime;

import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.Date;

public class AcademicActivityTimeDTO {
    private String id;
    private String institutionId;
    private String institutionName;
    private String sex;
    private Integer seq;
    private String startTime;
    private String endTime;
    private String description;

    public AcademicActivityTimeDTO() {
    }

    public AcademicActivityTimeDTO(AcademicActivityTime t) {
        setId(t.getId());
        setInstitutionId(t.getInstitution().getId());
        setInstitutionName(t.getInstitution().getName());
        setSex(t.getSex());
        setSeq(t.getSeq());
        setStartTime(t.getStartTime());
        setEndTime(t.getEndTime());
        setDescription(t.getDescription());
    }

    public AcademicActivityTimeDTO(String id, String institutionId, String institutionName, String sex, Integer seq, String startTime, String endTime, String description) {
        this.id = id;
        this.institutionId = institutionId;
        this.institutionName = institutionName;
        this.sex = sex;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
