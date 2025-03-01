package id.smartpesantren.web.rest.vm;

import javax.validation.constraints.NotNull;

public class WorkingDayOffVM {
    private String id;
    @NotNull
    private String employeeId;

    @NotNull
    private Integer dayId;

    @NotNull
    private String newValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
