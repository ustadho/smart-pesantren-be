package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "hr_working_dayoff")
public class WorkingDayOff extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    PersonData employee;

    @ManyToOne
    @JoinColumn(name = "day_id", nullable = false)
    private Day day;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonData getEmployee() {
        return employee;
    }

    public void setEmployee(PersonData employee) {
        this.employee = employee;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
