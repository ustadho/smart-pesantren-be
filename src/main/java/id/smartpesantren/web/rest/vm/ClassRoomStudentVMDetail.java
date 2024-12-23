package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.Student;

public class ClassRoomStudentVMDetail {
    private String id;
    private String studentId;
    private String studentName;
    private String studentNis;
    private String studentNisn;
    private String joinYear;
    private String attachment;

    public ClassRoomStudentVMDetail() {
    }

    public ClassRoomStudentVMDetail(String id, Student s) {
        setId(id);
        setStudentId(s.getId());
        setStudentNis(s.getNis());
        setStudentNisn(s.getNisn());
        setStudentName(s.getName());
        setJoinYear(s.getJoinYear().getCode());
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getJoinYear() {
        return joinYear;
    }

    public void setJoinYear(String joinYear) {
        this.joinYear = joinYear;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
