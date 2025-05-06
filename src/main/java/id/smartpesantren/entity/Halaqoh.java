package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "halaqoh")
public class Halaqoh {
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
    @JoinColumn(name = "pesantren_id", nullable = false)
    Pesantren pesantren;

    @ManyToMany
    @JoinTable(
        name = "halaqoh_musyrif",
        joinColumns = @JoinColumn(name = "halaqoh_id"),
        inverseJoinColumns = @JoinColumn(name = "musyrif_id")
    )
    List<PersonData> musyrifs;

    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "halaqoh", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<HalaqohStudent> students = new HashSet<>();

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

    public Pesantren getPesantren() {
        return pesantren;
    }

    public void setPesantren(Pesantren pesantren) {
        this.pesantren = pesantren;
    }

    public List<PersonData> getMusyrifs() {
        return musyrifs;
    }

    public void setMusyrifs(List<PersonData> musyrifs) {
        this.musyrifs = musyrifs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<HalaqohStudent> getStudents() {
        return students;
    }

    public void setStudents(Set<HalaqohStudent> students) {
        this.students = students;
    }
}
