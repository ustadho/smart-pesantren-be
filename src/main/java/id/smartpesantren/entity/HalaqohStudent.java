package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "halaqoh_student")
public class HalaqohStudent {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "halaqoh_id", nullable = false)
    @JsonBackReference
    private Halaqoh halaqoh;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    Student student;

    @Column(columnDefinition = "text")
    private String notes;

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

    public Halaqoh getHalaqoh() {
        return halaqoh;
    }

    public void setHalaqoh(Halaqoh halaqoh) {
        this.halaqoh = halaqoh;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
