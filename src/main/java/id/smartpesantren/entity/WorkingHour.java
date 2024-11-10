package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hr_working_hour")
public class WorkingHour extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    @Column(length = 4, nullable = false)
    private String code;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String color;

    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "workingHour", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<WorkingHourDetail> details = new HashSet<>();

    public WorkingHour() {
    }

    public WorkingHour(String id) {
        this.id = id;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<WorkingHourDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<WorkingHourDetail> details) {
        this.details = details;
    }
}
