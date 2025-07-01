package id.smartpesantren.service.dto;

import java.util.ArrayList;
import java.util.List;

public class PresensiHalaqohSantriVM {
    private String id;
    private Integer jumlahSantri;
    private Integer jumlahSantriAlpha;
    private Integer jumlahSantriIzin;
    private Integer jumlahSantriSakit;
    private List<PresensiHalaqohSantriVMDet> details = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PresensiHalaqohSantriVMDet> getDetails() {
        return details;
    }

    public void setDetails(List<PresensiHalaqohSantriVMDet> details) {
        this.details = details;
    }

    public Integer getJumlahSantri() {
        return jumlahSantri;
    }

    public void setJumlahSantri(Integer jumlahSantri) {
        this.jumlahSantri = jumlahSantri;
    }

    public Integer getJumlahSantriAlpha() {
        return jumlahSantriAlpha;
    }

    public void setJumlahSantriAlpha(Integer jumlahSantriAlpha) {
        this.jumlahSantriAlpha = jumlahSantriAlpha;
    }

    public Integer getJumlahSantriIzin() {
        return jumlahSantriIzin;
    }

    public void setJumlahSantriIzin(Integer jumlahSantriIzin) {
        this.jumlahSantriIzin = jumlahSantriIzin;
    }

    public Integer getJumlahSantriSakit() {
        return jumlahSantriSakit;
    }

    public void setJumlahSantriSakit(Integer jumlahSantriSakit) {
        this.jumlahSantriSakit = jumlahSantriSakit;
    }
}
