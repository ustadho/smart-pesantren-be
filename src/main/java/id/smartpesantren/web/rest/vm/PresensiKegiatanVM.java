package id.smartpesantren.web.rest.vm;

import com.fasterxml.jackson.annotation.JsonFormat;
import id.smartpesantren.entity.PresensiKegiatan;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class PresensiKegiatanVM {
    private String id;
    private String jenisKegiatanId;
    private String jenisKegiatanName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date tanggal;
    private String santriId;
    private String santriName;
    private String santriNis;
    private String santriNisn;
    private String santriClassName;
    private String penilaian;
    private String catatan;

    public PresensiKegiatanVM() {
    }

    public PresensiKegiatanVM(PresensiKegiatan p) {
        setId(p.getId());
        setJenisKegiatanId(p.getJenisKegiatan().getId());
        setJenisKegiatanName(p.getJenisKegiatan().getName());
        setSantriId(p.getSantri().getId());
        setSantriName(p.getSantri().getName());
        setSantriNis(p.getSantri().getNis());
        setSantriNisn(p.getSantri().getNisn());
        setSantriClassName(p.getSantri().getClassRoom().getName());
        setCatatan(p.getCatatan());
        setTanggal(p.getTanggal());
        setPenilaian(p.getPenilaian());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJenisKegiatanId() {
        return jenisKegiatanId;
    }

    public void setJenisKegiatanId(String jenisKegiatanId) {
        this.jenisKegiatanId = jenisKegiatanId;
    }

    public String getJenisKegiatanName() {
        return jenisKegiatanName;
    }

    public void setJenisKegiatanName(String jenisKegiatanName) {
        this.jenisKegiatanName = jenisKegiatanName;
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

    public String getSantriNis() {
        return santriNis;
    }

    public void setSantriNis(String santriNis) {
        this.santriNis = santriNis;
    }

    public String getSantriNisn() {
        return santriNisn;
    }

    public void setSantriNisn(String santriNisn) {
        this.santriNisn = santriNisn;
    }

    public String getSantriClassName() {
        return santriClassName;
    }

    public void setSantriClassName(String santriClassName) {
        this.santriClassName = santriClassName;
    }
}
