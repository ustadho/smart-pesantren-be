package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.EmployeeTransfer;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class EmployeeTransferVM {
    private String id;

    @NotNull
    String employeeId;

    String employeeName;

    @NotNull
    private Date effectiveDate;

    @NotNull
    private Integer typeId;

    @NotNull
    private String statusId;

    @NotNull
    private String organizationId;

    @NotNull
    private String sectionId;

    @NotNull
    private String positionId;
    private String managerId;
    private String attachment;
    private String description;

    public EmployeeTransferVM() {
    }

    public EmployeeTransferVM(EmployeeTransfer et) {
        this.setId(et.getId());
        this.setEmployeeId(et.getEmployee().getId());
        this.setEmployeeName(et.getEmployee().getName());
        this.setTypeId(et.getType().getId());
        this.setEffectiveDate(et.getEffectiveDate());
        this.setManagerId(et.getManager() == null? null: et.getManager().getId());
        this.setOrganizationId(et.getOrganization().getId());
        this.setSectionId(et.getSection().getId());
        this.setPositionId(et.getPosition().getId());
        this.setStatusId(et.getStatus().getId());
        this.setAttachment(et.getAttachment());
        this.setDescription(et.getDescription());

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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
