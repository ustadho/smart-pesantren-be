package id.smartpesantren.web.rest.vm;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PresenceKbmVMTeacher {
    private String id;
    private LocalDate presenceDate;
    @NotNull
    private String subjectScheduleTeacherId;
    @NotNull
    private String teacherId;
    private String note;
    private Integer statusId;
    private String kompetensiDasar;
    private String materi;
    private String indikator;
    private String fotoAbsen;
    private String pencapaian; //Tuntas
    private String attachment;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getPresenceDate() {
        return presenceDate;
    }

    public void setPresenceDate(LocalDate presenceDate) {
        this.presenceDate = presenceDate;
    }

    public String getSubjectScheduleTeacherId() {
        return subjectScheduleTeacherId;
    }

    public void setSubjectScheduleTeacherId(String subjectScheduleTeacherId) {
        this.subjectScheduleTeacherId = subjectScheduleTeacherId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getKompetensiDasar() {
        return kompetensiDasar;
    }

    public void setKompetensiDasar(String kompetensiDasar) {
        this.kompetensiDasar = kompetensiDasar;
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
    }

    public String getIndikator() {
        return indikator;
    }

    public void setIndikator(String indikator) {
        this.indikator = indikator;
    }

    public String getFotoAbsen() {
        return fotoAbsen;
    }

    public void setFotoAbsen(String fotoAbsen) {
        this.fotoAbsen = fotoAbsen;
    }

    public String getPencapaian() {
        return pencapaian;
    }

    public void setPencapaian(String pencapaian) {
        this.pencapaian = pencapaian;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
