package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "hr_working_time")
public class WorkingTime extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    @Column(length = 4, nullable = false)
    private String code;
    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "check_in_time", columnDefinition = "time with time zone")
    private LocalTime checkInTime;

    @Column(name = "check_out_time", columnDefinition = "time with time zone")
    private LocalTime checkOutTime;

    @Column(name = "prev_day")
    private Boolean previousDay = Boolean.FALSE;

    @Column(name = "next_day")
    private Boolean nextDate = Boolean.FALSE;

    @Column(name = "late_tolerance")
    private Integer lateTolerance;

    @Column(name = "early_leave_tolerance")
    private Integer earlyLeaveTolerance;

    @Column(name = "scan_start_check_in_time", columnDefinition = "time with time zone")
    private LocalTime scanStartCheckInTime;

    @Column(name = "scan_end_check_in_time", columnDefinition = "time with time zone")
    private LocalTime scanEndCheckInTime;

    @Column(name = "scan_start_check_out_time", columnDefinition = "time with time zone")
    private LocalTime scanStartCheckOutTime;

    @Column(name = "scan_end_check_out_time", columnDefinition = "time with time zone")
    private LocalTime scanEndCheckOutTime;

    @Column(name = "color", length = 10)
    private String color;

    @Column(name = "noOfWorkingDays")
    private Integer noOfWorkingDays;

    public WorkingTime() {
    }

    public WorkingTime(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Foundation getFoundation() {
        return foundation;
    }

    public void setFoundation(Foundation foundation) {
        this.foundation = foundation;
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

    public void setScanStartCheckInTime(LocalTime scanStartCheckInTime) {
        this.scanStartCheckInTime = scanStartCheckInTime;
    }

    public void setScanEndCheckInTime(LocalTime scanEndCheckInTime) {
        this.scanEndCheckInTime = scanEndCheckInTime;
    }

    public void setScanStartCheckOutTime(LocalTime scanStartCheckOutTime) {
        this.scanStartCheckOutTime = scanStartCheckOutTime;
    }

    public void setScanEndCheckOutTime(LocalTime scanEndCheckOutTime) {
        this.scanEndCheckOutTime = scanEndCheckOutTime;
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

    public LocalTime getScanStartCheckInTime() {
        return scanStartCheckInTime;
    }

    public LocalTime getScanEndCheckInTime() {
        return scanEndCheckInTime;
    }

    public LocalTime getScanStartCheckOutTime() {
        return scanStartCheckOutTime;
    }

    public LocalTime getScanEndCheckOutTime() {
        return scanEndCheckOutTime;
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
