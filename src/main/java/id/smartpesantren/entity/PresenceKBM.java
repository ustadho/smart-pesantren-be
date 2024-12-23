package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ac_presence_kbm")
public class PresenceKBM extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "subject_schedule_id", nullable = false)
    private SubjectSchedule subjectSchedule;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private PersonData teacher;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date presenceDate;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "presence_status_id", nullable = false)
    PresenceStatus presenceStatus;

    @Column(columnDefinition = "text")
    private String note;

    private String attachment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public PresenceStatus getPresenceStatus() {
        return presenceStatus;
    }

    public void setPresenceStatus(PresenceStatus presenceStatus) {
        this.presenceStatus = presenceStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public SubjectSchedule getSubjectSchedule() {
        return subjectSchedule;
    }

    public void setSubjectSchedule(SubjectSchedule subjectSchedule) {
        this.subjectSchedule = subjectSchedule;
    }

    public PersonData getTeacher() {
        return teacher;
    }

    public void setTeacher(PersonData teacher) {
        this.teacher = teacher;
    }

    public Date getPresenceDate() {
        return presenceDate;
    }

    public void setPresenceDate(Date presenceDate) {
        this.presenceDate = presenceDate;
    }
}
