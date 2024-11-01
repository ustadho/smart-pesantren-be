package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "hr_transfer_type")
public class HRTransferType {
    @Id
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    public HRTransferType() {
    }

    public HRTransferType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
