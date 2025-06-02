package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "presensi_asrama")
public class PresensiAsrama extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    @Column(length = 2, columnDefinition = "varchar default 'PR'") // DEFAULT 'PR'
    private String presenceType; //PR: Presensi; PL: Kepulangan: DT: Kedatangan

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, columnDefinition = "timestamp without time zone")
    private Date tanggal;

    @ManyToOne
    @JoinColumn(name = "santri_id", nullable = false)
    private Student santri;

    @ManyToOne
    @JoinColumn(name = "asrama_id", nullable = false)
    private Asrama asrama;

    @ManyToOne
    @JoinColumn(name = "presence_status_id", nullable = false)
    PresenceStatus presenceStatus;

    @Column(columnDefinition = "text")
    private String catatan;

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

    public String getPresenceType() {
        return presenceType;
    }

    public void setPresenceType(String presenceType) {
        this.presenceType = presenceType;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Student getSantri() {
        return santri;
    }

    public void setSantri(Student santri) {
        this.santri = santri;
    }

    public Asrama getAsrama() {
        return asrama;
    }

    public void setAsrama(Asrama asrama) {
        this.asrama = asrama;
    }

    public PresenceStatus getPresenceStatus() {
        return presenceStatus;
    }

    public void setPresenceStatus(PresenceStatus presenceStatus) {
        this.presenceStatus = presenceStatus;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
