package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.StudentPresence;

import java.util.Date;

public class StudentPresenceVM {
    private String id;
    private String studentId;
    private String studentName;
    private String nis;
    private String nisn;
    private Integer presenceStatusId;
    private String presenceStatusName;
    private Date date;
    private String note;
    private String attachment;

    public StudentPresenceVM() {
    }

    public StudentPresenceVM(StudentPresence p) {
        setId(p.getId());
        setStudentId(p.getStudent().getId());
        setStudentName(p.getStudent().getName());
        setNis(p.getStudent().getNis());
        setNisn(p.getStudent().getNisn());
        setPresenceStatusId(p.getPresenceStatus().getId());
        setPresenceStatusName(p.getPresenceStatus().getName());
        setDate(p.getDate());
        setNote(p.getNote());
        setAttachment(p.getAttachment());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getPresenceStatusName() {
        return presenceStatusName;
    }

    public void setPresenceStatusName(String presenceStatusName) {
        this.presenceStatusName = presenceStatusName;
    }

    public Integer getPresenceStatusId() {
        return presenceStatusId;
    }

    public void setPresenceStatusId(Integer presenceStatusId) {
        this.presenceStatusId = presenceStatusId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
