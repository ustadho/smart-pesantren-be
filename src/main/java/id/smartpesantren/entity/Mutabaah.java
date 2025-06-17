package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mutabaah")
public class Mutabaah extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    // refer ke id.smartpesantren.constant.MutabaahType
    @Column(nullable = false)
    private String tipe; //ZIYADAH, MUROJAAH, UJIAN

    @ManyToOne
    @JoinColumn(name = "pembimbing_id", nullable = false)
    PersonData pembimbing;

    @ManyToOne
    @JoinColumn(name = "santri_id", nullable = false)
    Student santri;

    @Column(nullable = false)
    private Date tanggal;

    @ManyToOne
    @JoinColumn(name = "waktu_id", nullable = false)
    private TahfidzTime waktu;

    @ManyToOne
    @JoinColumn(name = "dari_halaman_id", nullable = false)
    private TahfidzKonversi dari;

    @ManyToOne
    @JoinColumn(name = "sampai_halaman_id", nullable = false)
    private TahfidzKonversi sampai;

    @Column(columnDefinition = "int default 0")
    private Integer jumlahHalaman;

    @Column(length = 1)
    private String nilai;

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

    public PersonData getPembimbing() {
        return pembimbing;
    }

    public void setPembimbing(PersonData pembimbing) {
        this.pembimbing = pembimbing;
    }

    public Student getSantri() {
        return santri;
    }

    public void setSantri(Student santri) {
        this.santri = santri;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public TahfidzTime getWaktu() {
        return waktu;
    }

    public void setWaktu(TahfidzTime waktu) {
        this.waktu = waktu;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public TahfidzKonversi getDari() {
        return dari;
    }

    public void setDari(TahfidzKonversi dari) {
        this.dari = dari;
    }

    public TahfidzKonversi getSampai() {
        return sampai;
    }

    public void setSampai(TahfidzKonversi sampai) {
        this.sampai = sampai;
    }

    public Integer getJumlahHalaman() {
        return jumlahHalaman;
    }

    public void setJumlahHalaman(Integer jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
