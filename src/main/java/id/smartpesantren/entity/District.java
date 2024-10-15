package id.smartpesantren.entity;

import javax.persistence.*;

@Entity
@Table(name = "m_district") // Kecamatan
public class District extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    City city;

    @Column(length = 6, nullable = false, unique = true)
    private String code;
    @Column(length = 100, nullable = false)
    private String name;
    private String description;

    public District() {
    }

    public District(Integer id, City city, String code, String name, String description) {
        this.id = id;
        this.city = city;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
}
