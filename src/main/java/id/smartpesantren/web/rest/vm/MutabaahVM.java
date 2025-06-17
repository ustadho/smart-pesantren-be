package id.smartpesantren.web.rest.vm;

import java.util.Date;

public class MutabaahVM {
    private String id;
    private String tipe;
    private String pembimbingId;
    private String studentId;
    private String studentName;
    private String studentNis;
    private String studentNisn;
    private Date tanggal;
    private Integer waktuId;
    private Integer dariHalamanId;
    private Integer sampaiHalamanId;
    private Integer jumlahHalaman;
    private String catatan;
    private String nilai;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getPembimbingId() {
        return pembimbingId;
    }

    public void setPembimbingId(String pembimbingId) {
        this.pembimbingId = pembimbingId;
    }

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

    public String getStudentNis() {
        return studentNis;
    }

    public void setStudentNis(String studentNis) {
        this.studentNis = studentNis;
    }

    public String getStudentNisn() {
        return studentNisn;
    }

    public void setStudentNisn(String studentNisn) {
        this.studentNisn = studentNisn;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getWaktuId() {
        return waktuId;
    }

    public void setWaktuId(Integer waktuId) {
        this.waktuId = waktuId;
    }

    public Integer getDariHalamanId() {
        return dariHalamanId;
    }

    public void setDariHalamanId(Integer dariHalamanId) {
        this.dariHalamanId = dariHalamanId;
    }

    public Integer getSampaiHalamanId() {
        return sampaiHalamanId;
    }

    public void setSampaiHalamanId(Integer sampaiHalamanId) {
        this.sampaiHalamanId = sampaiHalamanId;
    }

    public Integer getJumlahHalaman() {
        return jumlahHalaman;
    }

    public void setJumlahHalaman(Integer jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }
}
