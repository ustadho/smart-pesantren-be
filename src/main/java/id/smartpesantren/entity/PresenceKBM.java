package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ac_presence_kbm")
public class PresenceKBM extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column(nullable = false, columnDefinition = "date default current_date")
    private LocalDate presenceDate;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    @JsonBackReference
    SubjectScheduleTeacher subjectScheduleTeacher;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private PersonData teacher;

    @ManyToOne
    @JoinColumn(name = "presence_status_id", nullable = false)
    PresenceStatus presenceStatus;

    @Column(columnDefinition = "text")
    private String note;

    @OneToMany(mappedBy = "presenceKBM", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<PresenceKBMStudent> students = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getPresenceDate() {
        return presenceDate;
    }

    public void setPresenceDate(LocalDate presenceDate) {
        this.presenceDate = presenceDate;
    }

    public SubjectScheduleTeacher getSubjectScheduleTeacher() {
        return subjectScheduleTeacher;
    }

    public void setSubjectScheduleTeacher(SubjectScheduleTeacher subjectScheduleTeacher) {
        this.subjectScheduleTeacher = subjectScheduleTeacher;
    }

    public PersonData getTeacher() {
        return teacher;
    }

    public void setTeacher(PersonData teacher) {
        this.teacher = teacher;
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

    public Set<PresenceKBMStudent> getStudents() {
        return students;
    }

    public void setStudents(Set<PresenceKBMStudent> students) {
        this.students = students;
    }
}
