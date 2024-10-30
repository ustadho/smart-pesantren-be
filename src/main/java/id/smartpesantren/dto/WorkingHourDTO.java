package id.smartpesantren.dto;

import id.smartpesantren.entity.WorkingHour;

import java.util.Date;

public class WorkingHourDTO {
    private String id;
    private String code;
    private String name;
    private Date checkInTime;
    private Date checkOutTime;
    private Boolean previousDay = Boolean.FALSE;
    private Boolean nextDate = Boolean.FALSE;
    private Integer lateTolerance;
    private Integer earlyLeaveTolerance;
    private Date scanStartCheckInTime;
    private Date scanEndCheckInTime;
    private Date scanStartCheckOutTime;
    private Date scanEndCheckOutTime;
    private String color;
    private Integer noOfWorkingDays;

    public WorkingHourDTO() {
    }

    public WorkingHourDTO(WorkingHour w) {
        this(
            w.getId(),
            w.getCode(),
            w.getName(),
            w.getCheckInTime(),
            w.getCheckOutTime(),
            w.getPreviousDay(),
            w.getNextDate(),
            w.getLateTolerance(),
            w.getEarlyLeaveTolerance(),
            w.getScanStartCheckInTime(),
            w.getScanEndCheckInTime(),
            w.getScanStartCheckOutTime(),
            w.getScanEndCheckOutTime(),
            w.getColor(),
            w.getNoOfWorkingDays()
        );
    }

    public WorkingHourDTO(String id, String code, String name, Date checkInTime, Date checkOutTime, Boolean previousDay, Boolean nextDate, Integer lateTolerance, Integer earlyLeaveTolerance, Date scanStartCheckInTime, Date scanEndCheckInTime, Date scanStartCheckOutTime, Date scanEndCheckOutTime, String color, Integer noOfWorkingDays) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.previousDay = previousDay;
        this.nextDate = nextDate;
        this.lateTolerance = lateTolerance;
        this.earlyLeaveTolerance = earlyLeaveTolerance;
        this.scanStartCheckInTime = scanStartCheckInTime;
        this.scanEndCheckInTime = scanEndCheckInTime;
        this.scanStartCheckOutTime = scanStartCheckOutTime;
        this.scanEndCheckOutTime = scanEndCheckOutTime;
        this.color = color;
        this.noOfWorkingDays = noOfWorkingDays;
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

    public Boolean getPreviousDay() {
        return previousDay;
    }

    public void setPreviousDay(Boolean previousDay) {
        this.previousDay = previousDay;
    }

    public Boolean getNextDate() {
        return nextDate;
    }

    public void setNextDate(Boolean nextDate) {
        this.nextDate = nextDate;
    }

    public Integer getLateTolerance() {
        return lateTolerance;
    }

    public void setLateTolerance(Integer lateTolerance) {
        this.lateTolerance = lateTolerance;
    }

    public Integer getEarlyLeaveTolerance() {
        return earlyLeaveTolerance;
    }

    public void setEarlyLeaveTolerance(Integer earlyLeaveTolerance) {
        this.earlyLeaveTolerance = earlyLeaveTolerance;
    }

    public Date getScanStartCheckInTime() {
        return scanStartCheckInTime;
    }

    public void setScanStartCheckInTime(Date scanStartCheckInTime) {
        this.scanStartCheckInTime = scanStartCheckInTime;
    }

    public Date getScanEndCheckInTime() {
        return scanEndCheckInTime;
    }

    public void setScanEndCheckInTime(Date scanEndCheckInTime) {
        this.scanEndCheckInTime = scanEndCheckInTime;
    }

    public Date getScanStartCheckOutTime() {
        return scanStartCheckOutTime;
    }

    public void setScanStartCheckOutTime(Date scanStartCheckOutTime) {
        this.scanStartCheckOutTime = scanStartCheckOutTime;
    }

    public Date getScanEndCheckOutTime() {
        return scanEndCheckOutTime;
    }

    public void setScanEndCheckOutTime(Date scanEndCheckOutTime) {
        this.scanEndCheckOutTime = scanEndCheckOutTime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getNoOfWorkingDays() {
        return noOfWorkingDays;
    }

    public void setNoOfWorkingDays(Integer noOfWorkingDays) {
        this.noOfWorkingDays = noOfWorkingDays;
    }
}
