package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "presensi_halaqoh_santri",
        uniqueConstraints = @UniqueConstraint(
        columnNames = {"presensi_halaqoh_id", "santri_id"})
)
public class PresensiHalaqohSantri {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "santri_id", nullable = false)
    private Student santri;

    @ManyToOne
    @JoinColumn(name = "presensi_halaqoh_id", nullable = false)
    private PresensiHalaqoh presensiHalaqoh;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private PresenceStatus status;

    private String catatan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getSantri() {
        return santri;
    }

    public void setSantri(Student santri) {
        this.santri = santri;
    }

    public PresensiHalaqoh getPresensiHalaqoh() {
        return presensiHalaqoh;
    }

    public void setPresensiHalaqoh(PresensiHalaqoh presensiHalaqoh) {
        this.presensiHalaqoh = presensiHalaqoh;
    }

    public PresenceStatus getStatus() {
        return status;
    }

    public void setStatus(PresenceStatus status) {
        this.status = status;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
