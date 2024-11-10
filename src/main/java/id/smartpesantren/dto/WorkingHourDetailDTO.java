package id.smartpesantren.dto;

import java.util.Date;

public class WorkingHourDetailDTO {
    private String id;
    private Integer dayId;
    private String dayName;
    private String workingTimeId;
    private String workingTimeName;
    private Date checkInTime;
    private Date checkOutTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getWorkingTimeId() {
        return workingTimeId;
    }

    public void setWorkingTimeId(String workingTimeId) {
        this.workingTimeId = workingTimeId;
    }

    public String getWorkingTimeName() {
        return workingTimeName;
    }

    public void setWorkingTimeName(String workingTimeName) {
        this.workingTimeName = workingTimeName;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}
