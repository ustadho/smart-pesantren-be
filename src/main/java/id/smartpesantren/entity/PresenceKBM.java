package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ac_presence_kbm")
public class PresenceKBM extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    @ManyToOne
    @JoinColumn(name = "subject_schedule_id", nullable = false)
    private SubjectSchedule subjectSchedule;

    @Temporal(TemporalType.DATE)
    private Date presenceDate;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private PersonData teacher;

    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "presenceKBM", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<PresenceKBMStudent> students = new HashSet<>();

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

    public SubjectSchedule getSubjectSchedule() {
        return subjectSchedule;
    }

    public void setSubjectSchedule(SubjectSchedule subjectSchedule) {
        this.subjectSchedule = subjectSchedule;
    }

    public Date getPresenceDate() {
        return presenceDate;
    }

    public void setPresenceDate(Date presenceDate) {
        this.presenceDate = presenceDate;
    }

    public PersonData getTeacher() {
        return teacher;
    }

    public void setTeacher(PersonData teacher) {
        this.teacher = teacher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PresenceKBMStudent> getStudents() {
        return students;
    }

    public void setStudents(Set<PresenceKBMStudent> students) {
        this.students = students;
    }
}
