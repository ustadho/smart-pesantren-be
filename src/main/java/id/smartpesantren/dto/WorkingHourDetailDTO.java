package id.smartpesantren.dto;

import java.time.LocalTime;
import java.util.Date;

public class WorkingHourDetailDTO {
    private String id;
    private Integer dayId;
    private String dayName;
    private String workingTimeId;
    private String workingTimeName;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;

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

    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}
