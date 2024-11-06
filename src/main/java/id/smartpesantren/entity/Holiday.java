package id.smartpesantren.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "m_holiday")
@Entity
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private String description;

    @Column(columnDefinition = "boolean default false")
    private Boolean fixDate;

    private String type; //N: Libur Nasional, F: Libur Yayasan, S: Special/Khusus

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFixDate() {
        return fixDate;
    }

    public void setFixDate(Boolean fixDate) {
        this.fixDate = fixDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
