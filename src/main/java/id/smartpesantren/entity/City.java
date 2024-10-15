package id.smartpesantren.entity;

import javax.persistence.*;

@Entity
@Table(name = "m_city")
public class City extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    Province province;

    @Column(length = 4, nullable = false, unique = true)
    private String code;
    @Column(length = 100, nullable = false, unique = true)
    private String name;
    private String description;

    public City() {
    }

    public City(Integer id) {
        this.id = id;
    }

    public City(Integer id, Province province, String code, String name, String description) {
        this.id = id;
        this.province = province;
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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
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
