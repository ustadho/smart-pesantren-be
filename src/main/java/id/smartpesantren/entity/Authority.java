/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.smartpesantren.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author ustadho
 */
@Entity
@Table(name = "c_Security_authority")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Authority implements Serializable {


    @Size(min = 0)
    @Id
    @Column(length = 50, nullable = false)
    private String name;

    @Column(name = "enable_in_pos", nullable = false, columnDefinition = "boolean default false")
    private Boolean enableInPos;

    @Column(name = "enable_in_academic", nullable = false, columnDefinition = "boolean default false")
    private Boolean enableInAcademic;

    @Column(name = "enable_in_printing", nullable = false, columnDefinition = "boolean default false")
    private Boolean enableInPrinting;

    public Authority() {
    }

    public Authority(@Size(min = 0) String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnableInPos() {
        return enableInPos;
    }

    public void setEnableInPos(Boolean enableInPos) {
        this.enableInPos = enableInPos;
    }

    public Boolean getEnableInAcademic() {
        return enableInAcademic;
    }

    public void setEnableInAcademic(Boolean enableInAcademic) {
        this.enableInAcademic = enableInAcademic;
    }

    public Boolean getEnableInPrinting() {
        return enableInPrinting;
    }

    public void setEnableInPrinting(Boolean enableInPrinting) {
        this.enableInPrinting = enableInPrinting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Authority authority = (Authority) o;

        if (name != null ? !name.equals(authority.name) : authority.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Authority{" +
            "name='" + name + '\'' +
            "}";
    }
}