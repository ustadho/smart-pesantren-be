package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ac_subject_schedule")
public class SubjectSchedule extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "class_room_id", nullable = false)
    ClassRoom classRoom;

    @ManyToOne
    @JoinColumn(name = "day_id", nullable = false)
    Day day;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "activity_time_id", nullable = false)
    private AcademicActivityTime activityTime;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "ac_subject_schedule_teacher",
            joinColumns = {@JoinColumn(name = "schedule_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id", referencedColumnName = "id")})
    @BatchSize(size = 20)
    private Set<PersonData> teachers = new HashSet<>();

    public SubjectSchedule() {
    }

    public SubjectSchedule(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public AcademicActivityTime getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(AcademicActivityTime activityTime) {
        this.activityTime = activityTime;
    }

    public Set<PersonData> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<PersonData> teachers) {
        this.teachers = teachers;
    }
}
