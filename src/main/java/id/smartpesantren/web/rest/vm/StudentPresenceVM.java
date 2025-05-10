package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.StudentPresence;

import java.util.Date;

public class StudentPresenceVM {
    private String id;
    private String studentId;
    private String studentName;
    private String studentNis;
    private String studentNisn;
    private String studentClassRoom;
    private Integer presenceStatusId;
    private String presenceStatusName;
    private Date date;
    private Integer numDays;
    private String note;
    private String attachment;

    public StudentPresenceVM() {
    }

    public StudentPresenceVM(StudentPresence p) {
        setId(p.getId());
        setStudentId(p.getStudent().getId());
        setStudentName(p.getStudent().getName());
        setStudentNis(p.getStudent().getNis());
        setStudentNisn(p.getStudent().getNisn());
        setStudentClassRoom(p.getStudent().getClassRoom() == null ? "" : p.getStudent().getClassRoom().getName());
        setPresenceStatusId(p.getPresenceStatus().getId());
        setPresenceStatusName(p.getPresenceStatus().getName());
        setDate(p.getDate());
        setNumDays(p.getNumDays());
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

    public String getStudentClassRoom() {
        return studentClassRoom;
    }

    public void setStudentClassRoom(String studentClassRoom) {
        this.studentClassRoom = studentClassRoom;
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

    public Integer getNumDays() {
        return numDays;
    }

    public void setNumDays(Integer numDays) {
        this.numDays = numDays;
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
