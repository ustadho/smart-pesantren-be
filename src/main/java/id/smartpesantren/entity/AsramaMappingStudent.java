package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "asrama_mapping_student")
public class AsramaMappingStudent {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "asrama_mapping_id", nullable = false)
    @JsonBackReference
    private AsramaMapping asramaMapping;

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

    public AsramaMapping getAsramaMapping() {
        return asramaMapping;
    }

    public void setAsramaMapping(AsramaMapping asramaMapping) {
        this.asramaMapping = asramaMapping;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
