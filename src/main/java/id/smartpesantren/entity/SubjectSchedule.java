package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
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
    @JoinColumn(name = "activity_time_start_id")
    private AcademicActivityTime activityTimeStart;

    @ManyToOne
    @JoinColumn(name = "activity_time_end_id")
    private AcademicActivityTime activityTimeEnd;

    @OneToMany(mappedBy = "schedule", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<SubjectScheduleTeacher> subjectTeachers = new HashSet<>();

    @Column(columnDefinition = "integer default 1")
    private Integer duration;

    @Column(name = "deleted_by", length = 50)
    @JsonIgnore
    private String deletedBy;

    @Column(name = "deleted_date", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @JsonIgnore
    private Instant deletedDate;


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

    public Set<SubjectScheduleTeacher> getSubjectTeachers() {
        return subjectTeachers;
    }

    public void setSubjectTeachers(Set<SubjectScheduleTeacher> subjectTeachers) {
        this.subjectTeachers = subjectTeachers;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Instant getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Instant deletedDate) {
        this.deletedDate = deletedDate;
    }
}
