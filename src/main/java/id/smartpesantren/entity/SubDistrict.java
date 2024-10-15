package id.smartpesantren.entity;

import javax.persistence.*;

@Entity
@Table(name = "m_sub_district") // Kelurahan
public class SubDistrict extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    District district;

    @Column(length = 6, nullable = false, unique = true)
    private String code;
    @Column(length = 100, nullable = false)
    private String name;
    private String description;

    public SubDistrict() {
    }

    public SubDistrict(Integer id, District district, String code, String name, String description) {
        this.id = id;
        this.district = district;
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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
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
