package id.smartpesantren.dto;

import id.smartpesantren.entity.AcademicYear;
import id.smartpesantren.entity.Curriculum;

import java.util.Date;

public class AcademicYearDTO {
    private String id;
    private String code;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Boolean isDefault;
    private String curriculum;

    public AcademicYearDTO() {
    }

    public AcademicYearDTO(AcademicYear a) {
        setId(a.getId());
        setCode(a.getCode());
        setName(a.getName());
        setDefault(a.getDefault());
        setDescription(a.getDescription());
        setStartDate(a.getStartDate());
        setEndDate(a.getEndDate());
        setCurriculum(a.getCurriculum() == null? null: a.getCurriculum().getName());
    }

    public AcademicYearDTO(String id, String code, String name, String description, Date startDate, Date endDate, Boolean isDefault, String curriculum) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isDefault = isDefault;
        this.curriculum = curriculum;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }
}
