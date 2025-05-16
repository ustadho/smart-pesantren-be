package id.smartpesantren.entity;

import id.smartpesantren.dto.SettingPenilaianVM;
import id.smartpesantren.security.SecurityUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "c_setting_penilaian", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"institution_id"})
})
public class SettingPenilaian extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    private Foundation foundation;

    @ManyToOne
    @JoinColumn(name = "institution_id", nullable = false, unique = true)
    private Institution institution;

    @Column(name = "persen_harian")
    private BigDecimal persenHarian;

    @Column(name = "persen_ketrampilan")
    private BigDecimal persenKetrampilan;

    @Column(name = "persen_sikap")
    private BigDecimal persenSikap;

    @Column(name = "persen_pts")
    private BigDecimal persenPts;

    @Column(name = "persen_pas")
    private BigDecimal persenPas;

    public SettingPenilaian() {
    }

    public SettingPenilaian(SettingPenilaianVM vm) {
        this.id = vm.getId();
        this.foundation = new Foundation(SecurityUtils.getFoundationId().get());
        this.institution = new Institution(vm.getInstitutionId());
        this.persenHarian = vm.getPersenHarian();
        this.persenKetrampilan = vm.getPersenKetrampilan();
        this.persenSikap = vm.getPersenSikap();
        this.persenPts = vm.getPersenPts();
        this.persenPas = vm.getPersenPas();
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

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public BigDecimal getPersenHarian() {
        return persenHarian;
    }

    public void setPersenHarian(BigDecimal persenHarian) {
        this.persenHarian = persenHarian;
    }

    public BigDecimal getPersenKetrampilan() {
        return persenKetrampilan;
    }

    public void setPersenKetrampilan(BigDecimal persenKetrampilan) {
        this.persenKetrampilan = persenKetrampilan;
    }

    public BigDecimal getPersenSikap() {
        return persenSikap;
    }

    public void setPersenSikap(BigDecimal persenSikap) {
        this.persenSikap = persenSikap;
    }

    public BigDecimal getPersenPts() {
        return persenPts;
    }

    public void setPersenPts(BigDecimal persenPts) {
        this.persenPts = persenPts;
    }

    public BigDecimal getPersenPas() {
        return persenPas;
    }

    public void setPersenPas(BigDecimal persenPas) {
        this.persenPas = persenPas;
    }
}
