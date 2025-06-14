package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ac_subject_schedule_history")
public class SubjectScheduleHistory extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    private String subjectScheduleId;

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
    @JoinColumn(name = "teacher_id", nullable = false)
    private PersonData teacher;

    @ManyToOne
    @JoinColumn(name = "activity_time_start_id", nullable = false)
    private AcademicActivityTime activityTimeStart;

    @ManyToOne
    @JoinColumn(name = "activity_time_end_id", nullable = false)
    private AcademicActivityTime activityTimeEnd;

    private Integer duration;

    private String logActivity;

    public SubjectScheduleHistory() {
    }

    public SubjectScheduleHistory(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectScheduleId() {
        return subjectScheduleId;
    }

    public void setSubjectScheduleId(String subjectScheduleId) {
        this.subjectScheduleId = subjectScheduleId;
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

    public AcademicActivityTime getActivityTimeStart() {
        return activityTimeStart;
    }

    public void setActivityTimeStart(AcademicActivityTime activityTimeStart) {
        this.activityTimeStart = activityTimeStart;
    }

    public AcademicActivityTime getActivityTimeEnd() {
        return activityTimeEnd;
    }

    public void setActivityTimeEnd(AcademicActivityTime activityTimeEnd) {
        this.activityTimeEnd = activityTimeEnd;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public PersonData getTeacher() {
        return teacher;
    }

    public void setTeacher(PersonData teacher) {
        this.teacher = teacher;
    }

    public String getLogActivity() {
        return logActivity;
    }

    public void setLogActivity(String logActivity) {
        this.logActivity = logActivity;
    }
}
