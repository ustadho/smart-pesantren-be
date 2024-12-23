package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ac_presence_kbm_student")
public class PresenceKBMStudent {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "presence_id", nullable = false)
    @JsonBackReference
    PresenceKBM presenceKBM;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "presence_status_id", nullable = false)
    PresenceStatus presenceStatus;

    @Column(columnDefinition = "text")
    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PresenceKBM getPresenceKBM() {
        return presenceKBM;
    }

    public void setPresenceKBM(PresenceKBM presenceKBM) {
        this.presenceKBM = presenceKBM;
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
}
