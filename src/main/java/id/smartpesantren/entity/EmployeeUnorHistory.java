package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hr_employee_unor_history")
public class EmployeeUnorHistory {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private PersonData person;

    @ManyToOne
    @JoinColumn(name = "transfer_type_id", nullable = false)
    private HRTransferType transferType;

    @ManyToOne
    @JoinColumn(name = "employee_status_id", nullable = false)
    private EmployeeStatus status;

    @Column(nullable = false)
    private Date effectiveDate;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    Organization organization;

    @ManyToOne
    @JoinColumn(name = "section_id")
    Section section;

    @ManyToOne
    @JoinColumn(name = "job_position_id")
    JobPosition jobPosition;

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

    public PersonData getPerson() {
        return person;
    }

    public void setPerson(PersonData person) {
        this.person = person;
    }

    public HRTransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(HRTransferType transferType) {
        this.transferType = transferType;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
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

    public JobPosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
    }
}
