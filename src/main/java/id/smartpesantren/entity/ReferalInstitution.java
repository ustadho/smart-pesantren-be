package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "referal_institution")
public class ReferalInstitution extends AbstractAuditingEntity implements Serializable {
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

    private String address;

    @ManyToOne
    @JoinColumn(name = "subdistrict_id")
    private SubDistrict subdistrict;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private String description;

    @ManyToOne
    @JoinColumn(name = "education_level_id")
    private EducationLevel educationLevel;

    public ReferalInstitution() {
    }

    public ReferalInstitution(String id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SubDistrict getSubdistrict() {
        return subdistrict;
    }

    public void setSubdistrict(SubDistrict subdistrict) {
        this.subdistrict = subdistrict;
    }
}
