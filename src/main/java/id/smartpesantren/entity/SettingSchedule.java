package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ac_setting_schedule")
public class SettingSchedule extends AbstractAuditingEntity implements Serializable {
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
    @JoinColumn(name = "institution_id", nullable = false)
    private Institution institution;

    @Column(length = 1, columnDefinition = "varchar(1) default 'M'")
    private String sex;

    private String description;

    @ManyToOne
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;

    @OneToMany(mappedBy = "setting", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<SettingScheduleTeacher> teachers = new HashSet<>();

    public SettingSchedule(String id) {
        this.id = id;
    }

    public SettingSchedule() {
    }

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

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public Set<SettingScheduleTeacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<SettingScheduleTeacher> teachers) {
        this.teachers = teachers;
    }
}
