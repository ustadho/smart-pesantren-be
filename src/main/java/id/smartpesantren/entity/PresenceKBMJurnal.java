package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ac_presence_kbm_jurnal")
public class PresenceKBMJurnal extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @OneToOne
    @JoinColumn(name = "presence_kbm_id", nullable = false, unique = true)
    private PresenceKBM presenceKBM;

    @Column(columnDefinition = "text")
    private String materiPokok;

    @Column(columnDefinition = "text")
    private String kegiatan;

    @Column(columnDefinition = "text")
    private String penilaian;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PresenceKBM getPresenceKBM() {
        return presenceKBM;
    }

    public void setPresenceKBM(PresenceKBM presenceKBM) {
        this.presenceKBM = presenceKBM;
    }

    public String getMateriPokok() {
        return materiPokok;
    }

    public void setMateriPokok(String materiPokok) {
        this.materiPokok = materiPokok;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    public String getPenilaian() {
        return penilaian;
    }

    public void setPenilaian(String penilaian) {
        this.penilaian = penilaian;
    }
}
