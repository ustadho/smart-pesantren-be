package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "hr_working_hour_detail")
public class WorkingHourDetail {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "working_hour_id")
    @JsonBackReference
    private WorkingHour workingHour;

    @ManyToOne
    @JoinColumn(name = "working_time_id", nullable = false)
    WorkingTime workingTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public WorkingHour getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(WorkingHour workingHour) {
        this.workingHour = workingHour;
    }

    public WorkingHour getWorkingDay() {
        return workingHour;
    }

    public void setWorkingDay(WorkingHour workingHour) {
        this.workingHour = workingHour;
    }

    public WorkingTime getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(WorkingTime workingTime) {
        this.workingTime = workingTime;
    }
}
