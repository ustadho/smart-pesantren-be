package id.smartpesantren.entity;

import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.StudentCategoryVM;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ac_student_category")
public class StudentCategory {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    @Column(nullable = false)
    private String name;

    private String description;

    public StudentCategory() {
    }

    public StudentCategory(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Foundation getFoundation() {
        return foundation;
    }

    public void setFoundation(Foundation foundation) {
        this.foundation = foundation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StudentCategory fromVM(StudentCategoryVM vm) {
        setId(vm.getId());
        setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        setName(vm.getName());
        setDescription(vm.getDescription());
        return this;
    }
}
