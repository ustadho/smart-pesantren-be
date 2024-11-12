package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.AcademicYear;

import java.util.Date;

public class AcademicYearVM {
    private String id;
    private String code;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Boolean isDefault;
    private String curriculumId;

    public AcademicYearVM() {
    }

    public AcademicYearVM(AcademicYear a) {
        setId(a.getId());
        setName(a.getName());
        setCode(a.getCode());
        setDescription(a.getDescription());
        setCurriculumId(a.getCurriculum() == null? null: a.getCurriculum().getId());
        setIsDefault(a.getDefault());
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

    public String getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(String curriculumId) {
        this.curriculumId = curriculumId;
    }
}
