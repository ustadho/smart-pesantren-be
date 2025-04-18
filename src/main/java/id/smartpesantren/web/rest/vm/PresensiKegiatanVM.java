package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.PresensiKegiatan;

import java.time.LocalDate;

public class PresensiKegiatanVM {
    private String id;
    private String jenisKegiatanId;
    private String jenisKegiatanName;
    private LocalDate tanggal;
    private String santriId;
    private String santriName;
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

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
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
}
