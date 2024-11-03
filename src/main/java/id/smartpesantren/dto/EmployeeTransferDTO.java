package id.smartpesantren.dto;

import id.smartpesantren.entity.EmployeeTransfer;

import java.time.Instant;
import java.util.Date;

public class EmployeeTransferDTO {
    private String id;
    private String employeeId;
    private String employeeNo;
    private String employeeName;
    private String transferTypeName;
    private String organization;
    private String jobPosition;
    private String jobLevel;
    private String employeeStatus;
    private Date effectiveDate;
    private Date joinDate;
    private Instant createdDate;
    private String createdBy;

    public EmployeeTransferDTO() {
    }

    public EmployeeTransferDTO(EmployeeTransfer e) {
        this(e.getId(), e.getEmployee().getId(), e.getEmployee().getEmployeeNo(), e.getEmployee().getName(), e.getType().getName(),
                e.getOrganization().getName(), e.getPosition().getName(), e.getPosition().getJobLevel().getName(),
                e.getStatus().getName(), e.getEffectiveDate(), e.getEmployee().getJoinDate(), e.getCreatedDate(), e.getCreatedBy()
        );
    }

    public EmployeeTransferDTO(String id, String employeeId, String employeeNo, String employeeName, String transferType, String organization, String jobPosition, String jobLevel, String employeeStatus, Date effectiveDate, Date joinDate, Instant createdDate, String createdBy) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
        this.transferTypeName = transferType;
        this.organization = organization;
        this.jobPosition = jobPosition;
        this.jobLevel = jobLevel;
        this.employeeStatus = employeeStatus;
        this.effectiveDate = effectiveDate;
        this.joinDate = joinDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
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

    public String getTransferTypeName() {
        return transferTypeName;
    }

    public void setTransferTypeName(String transferTypeName) {
        this.transferTypeName = transferTypeName;
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

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
