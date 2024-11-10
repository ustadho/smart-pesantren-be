package id.smartpesantren.web.rest.vm;

import java.util.Date;

public class EmployeeWorkingHourVM {
    private String id;
    private Date effectiveDate;
    private String workingHourId;
    private String workingHourName;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkingHourId() {
        return workingHourId;
    }

    public void setWorkingHourId(String workingHourId) {
        this.workingHourId = workingHourId;
    }

    public String getWorkingHourName() {
        return workingHourName;
    }

    public void setWorkingHourName(String workingHourName) {
        this.workingHourName = workingHourName;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
