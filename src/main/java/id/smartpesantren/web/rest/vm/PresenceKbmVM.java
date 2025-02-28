package id.smartpesantren.web.rest.vm;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PresenceKbmVM {
    private String id;
    private LocalDate presenceDate;
    @NotNull
    private String subjectScheduleId;
    @NotNull
    private String teacherId;
    private String note;
    private Integer statusId;

    private List<PresenceKbmVMStudent> students = new ArrayList<>();

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

    public String getSubjectScheduleId() {
        return subjectScheduleId;
    }

    public void setSubjectScheduleId(String subjectScheduleId) {
        this.subjectScheduleId = subjectScheduleId;
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

    public List<PresenceKbmVMStudent> getStudents() {
        return students;
    }

    public void setStudents(List<PresenceKbmVMStudent> students) {
        this.students = students;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
}
