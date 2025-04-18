package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "presensi_kegiatan")
public class PresensiKegiatan extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    @Column(nullable = false, columnDefinition = "date default current_date")
    private LocalDate tanggal;

    @ManyToOne
    @JoinColumn(name = "santri_id", nullable = false)
    private Student santri;

    @ManyToOne
    @JoinColumn(name = "jenis_kegiatan_id", nullable = false)
    private JenisKegiatan jenisKegiatan;

    private String penilaian;

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

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public Student getSantri() {
        return santri;
    }

    public void setSantri(Student santri) {
        this.santri = santri;
    }

    public JenisKegiatan getJenisKegiatan() {
        return jenisKegiatan;
    }

    public void setJenisKegiatan(JenisKegiatan jenisKegiatan) {
        this.jenisKegiatan = jenisKegiatan;
    }

    public String getPenilaian() {
        return penilaian;
    }

    public void setPenilaian(String penilaian) {
        this.penilaian = penilaian;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
