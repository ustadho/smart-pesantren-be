package id.smartpesantren.service.dto;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

public class PresensiHalaqohVM {
    private String id;

    @NotNull
    private Date tanggal;

    @NotNull
    private Integer tahfidzTimeId;
    private String tahfidzTimeName;

    @NotNull
    private String halaqohId;
    private String halaqohName;
    private String pembimbingId;
    private String pembimbingName;
    private Integer presenceStatusId;
    private String presenceStatusName;
    private Instant presenceDate;
    private String presenceNote;
    private String attachment;
    private Integer jumlahSantri;
    private Integer jumlahSantriAlpha;
    private Integer jumlahSantriIzin;
    private Integer jumlahSantriSakit;


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

    public Integer getTahfidzTimeId() {
        return tahfidzTimeId;
    }

    public void setTahfidzTimeId(Integer tahfidzTimeId) {
        this.tahfidzTimeId = tahfidzTimeId;
    }

    public String getTahfidzTimeName() {
        return tahfidzTimeName;
    }

    public void setTahfidzTimeName(String tahfidzTimeName) {
        this.tahfidzTimeName = tahfidzTimeName;
    }

    public String getHalaqohId() {
        return halaqohId;
    }

    public void setHalaqohId(String halaqohId) {
        this.halaqohId = halaqohId;
    }

    public String getHalaqohName() {
        return halaqohName;
    }

    public void setHalaqohName(String halaqohName) {
        this.halaqohName = halaqohName;
    }

    public String getPembimbingId() {
        return pembimbingId;
    }

    public void setPembimbingId(String pembimbingId) {
        this.pembimbingId = pembimbingId;
    }

    public String getPembimbingName() {
        return pembimbingName;
    }

    public void setPembimbingName(String pembimbingName) {
        this.pembimbingName = pembimbingName;
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

    public Instant getPresenceDate() {
        return presenceDate;
    }

    public void setPresenceDate(Instant presenceDate) {
        this.presenceDate = presenceDate;
    }

    public String getPresenceNote() {
        return presenceNote;
    }

    public void setPresenceNote(String presenceNote) {
        this.presenceNote = presenceNote;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
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
