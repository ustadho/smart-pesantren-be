package id.smartpesantren.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person_title")
public class PersonTitle {
    @Id
    @Column(length = 4)
    private String id;

    @Column(length = 30)
    private String name;

    @Column(length = 1)
    private String sex; //M: Male, F: Female

    private Boolean active;

    @Column(columnDefinition = "smallint default 0")
    private Integer seq;

    public PersonTitle() {
    }

    public PersonTitle(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
