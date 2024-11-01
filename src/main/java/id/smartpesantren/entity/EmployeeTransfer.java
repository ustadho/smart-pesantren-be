package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hr_employee_transfer")
public class EmployeeTransfer {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    PersonData foundation;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    PersonData employee;

    @Temporal(TemporalType.DATE)
    private Date effectiveDate;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private HRTransferType type;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private EmployeeStatus status;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private JobPosition position;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private PersonData manager;

    private String attachment;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonData getFoundation() {
        return foundation;
    }

    public void setFoundation(PersonData foundation) {
        this.foundation = foundation;
    }

    public PersonData getEmployee() {
        return employee;
    }

    public void setEmployee(PersonData employee) {
        this.employee = employee;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public HRTransferType getType() {
        return type;
    }

    public void setType(HRTransferType type) {
        this.type = type;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public JobPosition getPosition() {
        return position;
    }

    public void setPosition(JobPosition position) {
        this.position = position;
    }

    public PersonData getManager() {
        return manager;
    }

    public void setManager(PersonData manager) {
        this.manager = manager;
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
