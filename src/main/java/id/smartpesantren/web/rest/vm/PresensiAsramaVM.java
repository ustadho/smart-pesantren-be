package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.Asrama;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.PresenceStatus;
import id.smartpesantren.entity.Student;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class PresensiAsramaVM {
    private String id;

    @NotNull
    private Date tanggal;

    private String presenceType;
    private String santriId;
    private String santriName;

    private String asramaId;
    private String asramaName;

    private Integer presenceStatusId;
    private String presenceStatusName;

    private String catatan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotNull Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(@NotNull Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getPresenceType() {
        return presenceType;
    }

    public void setPresenceType(String presenceType) {
        this.presenceType = presenceType;
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

    public String getAsramaId() {
        return asramaId;
    }

    public void setAsramaId(String asramaId) {
        this.asramaId = asramaId;
    }

    public String getAsramaName() {
        return asramaName;
    }

    public void setAsramaName(String asramaName) {
        this.asramaName = asramaName;
    }

    public Integer getPresenceStatusId() {
        return presenceStatusId;
    }

    public void setPresenceStatusId(Integer presenceStatusId) {
        this.presenceStatusId = presenceStatusId;
    }

    public String getPresenceStatusName() {
        return presenceStatusName;
    }

    public void setPresenceStatusName(String presenceStatusName) {
        this.presenceStatusName = presenceStatusName;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
