package id.smartpesantren.entity;

import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.ClassLevelVM;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ac_class_level")
public class ClassLevel extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private Short level;

    private String description;

    @Column(nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "education_level_id", nullable = false)
    private EducationLevel educationLevel;

    public ClassLevel() {
    }

    public ClassLevel(String id) {
        this.id = id;
    }

    public ClassLevel fromVM(ClassLevelVM vm) {
        setId(vm.getId());
        setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        setCode(vm.getCode());
        setName(vm.getName());
        setLevel(vm.getLevel());
        setDescription(vm.getDescription());
        setColor(vm.getColor());
        setEducationLevel(new EducationLevel(vm.getEducationLevelId()));
        return this;
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

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
