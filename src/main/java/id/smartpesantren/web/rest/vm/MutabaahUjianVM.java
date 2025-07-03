package id.smartpesantren.web.rest.vm;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MutabaahUjianVM {
    private String id;
    private Date tanggal;
    private String santriId;
    private String santriName;
    private String santriNis;
    private String juzs;
    private Integer totalJuz;
    private Integer totalHalaman;
    private Integer halamanAwal;
    private Integer halamanAkhir;
    private String pengujiId;
    private String pengujiName;
    private BigDecimal nilaiAngka;
    private String nilai;
    private String catatan;
    private List<MutabaahUjianVMDet> details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getSantriId() {
        return santriId;
    }

    public void setSantriId(String santriId) {
        this.santriId = santriId;
    }

    public String getSantriName() {
        return santriName;
    }

    public void setSantriName(String santriName) {
        this.santriName = santriName;
    }

    public String getSantriNis() {
        return santriNis;
    }

    public void setSantriNis(String santriNis) {
        this.santriNis = santriNis;
    }

    public String getJuzs() {
        return juzs;
    }

    public void setJuzs(String juzs) {
        this.juzs = juzs;
    }

    public Integer getTotalJuz() {
        return totalJuz;
    }

    public void setTotalJuz(Integer totalJuz) {
        this.totalJuz = totalJuz;
    }

    public Integer getTotalHalaman() {
        return totalHalaman;
    }

    public void setTotalHalaman(Integer totalHalaman) {
        this.totalHalaman = totalHalaman;
    }

    public Integer getHalamanAwal() {
        return halamanAwal;
    }

    public void setHalamanAwal(Integer halamanAwal) {
        this.halamanAwal = halamanAwal;
    }

    public Integer getHalamanAkhir() {
        return halamanAkhir;
    }

    public void setHalamanAkhir(Integer halamanAkhir) {
        this.halamanAkhir = halamanAkhir;
    }

    public String getPengujiId() {
        return pengujiId;
    }

    public void setPengujiId(String pengujiId) {
        this.pengujiId = pengujiId;
    }

    public String getPengujiName() {
        return pengujiName;
    }

    public void setPengujiName(String pengujiName) {
        this.pengujiName = pengujiName;
    }

    public BigDecimal getNilaiAngka() {
        return nilaiAngka;
    }

    public void setNilaiAngka(BigDecimal nilaiAngka) {
        this.nilaiAngka = nilaiAngka;
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

    public List<MutabaahUjianVMDet> getDetails() {
        return details;
    }

    public void setDetails(List<MutabaahUjianVMDet> details) {
        this.details = details;
    }
}
