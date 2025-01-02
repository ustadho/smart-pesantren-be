package id.smartpesantren.entity;

import id.smartpesantren.constant.Sex;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "asrama")
public class Asrama {
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

    @Column(length = 255, nullable = false)
    private String name;

    private String description;

    @Column(columnDefinition = "boolean default true")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    Building building;

    @Column(length = 1, columnDefinition = "varchar(1) default 'M'")
    private String sex;

    @ManyToOne
    @JoinColumn(name = "pesantren_id")
    private Pesantren pesantren;

    public Asrama() {
    }

    public Asrama(String id) {
        this.id = id;
    }

    private Short capacity;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Pesantren getPesantren() {
        return pesantren;
    }

    public void setPesantren(Pesantren pesantren) {
        this.pesantren = pesantren;
    }

    public Short getCapacity() {
        return capacity;
    }

    public void setCapacity(Short capacity) {
        this.capacity = capacity;
    }
}
