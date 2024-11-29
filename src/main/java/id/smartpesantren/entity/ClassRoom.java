package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ac_class_room")
public class ClassRoom extends AbstractAuditingEntity implements Serializable {
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

    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private ClassLevel classLevel;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String room;

    @Column(length = 1, columnDefinition = "varchar(1) default 'M'")
    private String sex;

    private Short capacity;

    private String description;

    @ManyToOne
    @JoinColumn(name = "home_room_teacher_id", nullable = false)
    private PersonData homeRoomTeacher;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;

    @OneToMany(mappedBy = "classRoom", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<ClassRoomStudent> students = new HashSet<>();

    public ClassRoom(String id) {
        this.id = id;
    }

    public ClassRoom() {
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

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public id.smartpesantren.entity.ClassLevel getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(id.smartpesantren.entity.ClassLevel classLevel) {
        this.classLevel = classLevel;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public Foundation getFoundation() {
        return foundation;
    }

    public void setFoundation(Foundation foundation) {
        this.foundation = foundation;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public PersonData getHomeRoomTeacher() {
        return homeRoomTeacher;
    }

    public void setHomeRoomTeacher(PersonData homeRoomTeacher) {
        this.homeRoomTeacher = homeRoomTeacher;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public Set<ClassRoomStudent> getStudents() {
        return students;
    }

    public void setStudents(Set<ClassRoomStudent> students) {
        this.students = students;
    }
}
