package id.smartpesantren.dto;

import id.smartpesantren.entity.EmployeeTransfer;

import java.util.Date;

public class EmployeeTransferDTO {
    private String id;
    private String employeeId;
    private String employeeNo;
    private String employeeName;
    private String organization;
    private String jobPosition;
    private String jobLevel;
    private String employeeStatus;
    private Date effectiveDate;
    private Date joinDate;

    public EmployeeTransferDTO() {
    }

    public EmployeeTransferDTO(EmployeeTransfer e) {
        this(e.getId(), e.getEmployee().getId(), e.getEmployee().getEmployeeNo(), e.getEmployee().getName(),
                e.getOrganization().getName(), e.getPosition().getName(), e.getPosition().getJobLevel().getName(),
                e.getStatus().getName(), e.getEffectiveDate(), e.getEmployee().getJoinDate()
        );
    }

    public EmployeeTransferDTO(String id, String employeeId, String employeeNo, String employeeName, String organization, String jobPosition, String jobLevel, String employeeStatus, Date effectiveDate, Date joinDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
        this.organization = organization;
        this.jobPosition = jobPosition;
        this.jobLevel = jobLevel;
        this.employeeStatus = employeeStatus;
        this.effectiveDate = effectiveDate;
        this.joinDate = joinDate;
    }

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

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}
