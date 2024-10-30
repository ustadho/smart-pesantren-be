package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "hr_working_hour")
public class WorkingHour extends AbstractAuditingEntity implements Serializable {
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

    @Temporal(TemporalType.TIME)
    @Column(name = "check_in_time")
    private Date checkInTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "check_out_time")
    private Date checkOutTime;

    @Column(name = "prev_day")
    private Boolean previousDay = Boolean.FALSE;

    @Column(name = "next_day")
    private Boolean nextDate = Boolean.FALSE;

    @Column(name = "late_tolerance")
    private Integer lateTolerance;

    @Column(name = "early_leave_tolerance")
    private Integer earlyLeaveTolerance;

    @Temporal(TemporalType.TIME)
    @Column(name = "scan_start_check_in_time")
    private Date scanStartCheckInTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "scan_end_check_in_time")
    private Date scanEndCheckInTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "scan_start_check_out_time")
    private Date scanStartCheckOutTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "scan_end_check_out_time")
    private Date scanEndCheckOutTime;

    @Column(name = "color", length = 10)
    private String color;

    @Column(name = "noOfWorkingDays")
    private Integer noOfWorkingDays;

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
