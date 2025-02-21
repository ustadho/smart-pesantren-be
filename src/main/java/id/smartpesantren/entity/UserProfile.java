package id.smartpesantren.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "c_security_profile")
public class UserProfile{
    @Id
    private Integer id;

    @Column(nullable = false, length = 30)
    private String name;

    public UserProfile() {
    }

    public UserProfile(Integer id) {
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
