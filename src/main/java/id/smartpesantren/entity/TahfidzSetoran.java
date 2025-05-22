package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tahfidz_setoran")
public class TahfidzSetoran {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    Student student;


    private Date tanggal;

    private String waktu;

    private Integer targetMulai;
    private Integer targetSampai;

    private String qiraah;
    private String hifdz;

    private Integer tikrar;

    private Integer setorUlangDari;
    private Integer setorUlangSampai;

    private String murajaah;

    private BigDecimal nilai;

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public Integer getTargetMulai() {
        return targetMulai;
    }

    public void setTargetMulai(Integer targetMulai) {
        this.targetMulai = targetMulai;
    }

    public Integer getTargetSampai() {
        return targetSampai;
    }

    public void setTargetSampai(Integer targetSampai) {
        this.targetSampai = targetSampai;
    }

    public String getQiraah() {
        return qiraah;
    }

    public void setQiraah(String qiraah) {
        this.qiraah = qiraah;
    }

    public String getHifdz() {
        return hifdz;
    }

    public void setHifdz(String hifdz) {
        this.hifdz = hifdz;
    }

    public Integer getTikrar() {
        return tikrar;
    }

    public void setTikrar(Integer tikrar) {
        this.tikrar = tikrar;
    }

    public Integer getSetorUlangDari() {
        return setorUlangDari;
    }

    public void setSetorUlangDari(Integer setorUlangDari) {
        this.setorUlangDari = setorUlangDari;
    }

    public Integer getSetorUlangSampai() {
        return setorUlangSampai;
    }

    public void setSetorUlangSampai(Integer setorUlangSampai) {
        this.setorUlangSampai = setorUlangSampai;
    }

    public String getMurajaah() {
        return murajaah;
    }

    public void setMurajaah(String murajaah) {
        this.murajaah = murajaah;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }
}
