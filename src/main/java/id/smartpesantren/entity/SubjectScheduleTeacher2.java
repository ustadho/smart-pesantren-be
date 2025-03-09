package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ac_subject_schedule_teacher2")
public class SubjectScheduleTeacher2 {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    SubjectSchedule2 schedule;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    PersonData teacher;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SubjectSchedule2 getSchedule() {
        return schedule;
    }

    public void setSchedule(SubjectSchedule2 schedule) {
        this.schedule = schedule;
    }

    public PersonData getTeacher() {
        return teacher;
    }

    public void setTeacher(PersonData teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }


}
