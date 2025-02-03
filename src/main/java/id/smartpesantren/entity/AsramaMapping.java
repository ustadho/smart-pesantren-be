package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "asrama_mapping")
public class AsramaMapping {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    @ManyToOne
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @ManyToOne
    @JoinColumn(name = "asrama_id", nullable = false)
    Asrama asrama;

    @ManyToOne
    @JoinColumn(name = "musyrif_id", nullable = false)
    PersonData musyrif;

    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "asramaMapping", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<AsramaMappingStudent> students = new HashSet<>();

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

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public Asrama getAsrama() {
        return asrama;
    }

    public void setAsrama(Asrama asrama) {
        this.asrama = asrama;
    }

    public PersonData getMusyrif() {
        return musyrif;
    }

    public void setMusyrif(PersonData musyrif) {
        this.musyrif = musyrif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AsramaMappingStudent> getStudents() {
        return students;
    }

    public void setStudents(Set<AsramaMappingStudent> students) {
        this.students = students;
    }
}
