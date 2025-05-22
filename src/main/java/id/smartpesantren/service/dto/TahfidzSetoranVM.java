package id.smartpesantren.service.dto;

import java.math.BigDecimal;

public class TahfidzSetoranVM {
    private String studentId;
    private String studentName;
    private String tanggal;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
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
