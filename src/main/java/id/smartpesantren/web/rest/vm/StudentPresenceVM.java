package id.smartpesantren.web.rest.vm;

import java.util.Date;

public class StudentPresenceVM {
    private String id;
    private String studentId;
    private Integer presenceStatusId;
    private Date date;
    private String note;
    private String attachment;

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
