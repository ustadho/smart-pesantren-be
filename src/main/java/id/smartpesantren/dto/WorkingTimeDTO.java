package id.smartpesantren.dto;

import id.smartpesantren.entity.WorkingTime;

import java.time.LocalTime;
import java.util.Date;

public class WorkingTimeDTO {
    private String id;
    private String code;
    private String name;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private Boolean previousDay = Boolean.FALSE;
    private Boolean nextDate = Boolean.FALSE;
    private Integer lateTolerance;
    private Integer earlyLeaveTolerance;
    private LocalTime scanStartCheckInTime;
    private LocalTime scanEndCheckInTime;
    private LocalTime scanStartCheckOutTime;
    private LocalTime scanEndCheckOutTime;
    private String color;
    private Integer noOfWorkingDays;

    public WorkingTimeDTO() {
    }

    public WorkingTimeDTO(WorkingTime w) {
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

    public WorkingTimeDTO(String id, String code, String name, LocalTime checkInTime, LocalTime checkOutTime, Boolean previousDay, Boolean nextDate, Integer lateTolerance, Integer earlyLeaveTolerance, LocalTime scanStartCheckInTime, LocalTime scanEndCheckInTime, LocalTime scanStartCheckOutTime, LocalTime scanEndCheckOutTime, String color, Integer noOfWorkingDays) {
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

    public LocalTime getScanStartCheckInTime() {
        return scanStartCheckInTime;
    }

    public void setScanStartCheckInTime(LocalTime scanStartCheckInTime) {
        this.scanStartCheckInTime = scanStartCheckInTime;
    }

    public LocalTime getScanEndCheckInTime() {
        return scanEndCheckInTime;
    }

    public void setScanEndCheckInTime(LocalTime scanEndCheckInTime) {
        this.scanEndCheckInTime = scanEndCheckInTime;
    }

    public LocalTime getScanStartCheckOutTime() {
        return scanStartCheckOutTime;
    }

    public void setScanStartCheckOutTime(LocalTime scanStartCheckOutTime) {
        this.scanStartCheckOutTime = scanStartCheckOutTime;
    }

    public LocalTime getScanEndCheckOutTime() {
        return scanEndCheckOutTime;
    }

    public void setScanEndCheckOutTime(LocalTime scanEndCheckOutTime) {
        this.scanEndCheckOutTime = scanEndCheckOutTime;
    }
}
