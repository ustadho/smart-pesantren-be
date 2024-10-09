package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "class_room")
public class ClassRoom extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private ClassLevel level;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private Short capacity;

    private String description;

    public ClassRoom(String id) {
        this.id = id;
    }

    public ClassRoom() {
    }

    public ClassRoom(String id, AcademicYear academicYear, id.smartpesantren.entity.ClassLevel level, String code, String name, Short capacity, String description) {
        this.id = id;
        this.academicYear = academicYear;
        this.level = level;
        this.code = code;
        this.name = name;
        this.capacity = capacity;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public id.smartpesantren.entity.AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(id.smartpesantren.entity.AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public id.smartpesantren.entity.ClassLevel getLevel() {
        return level;
    }

    public void setLevel(id.smartpesantren.entity.ClassLevel level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getCapacity() {
        return capacity;
    }

    public void setCapacity(Short capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
