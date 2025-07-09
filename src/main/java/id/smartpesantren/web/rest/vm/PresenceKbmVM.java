package id.smartpesantren.web.rest.vm;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PresenceKbmVM {
    private String id;
    private Instant presenceDate;
    @NotNull
    private String subjectScheduleTeacherId;
    @NotNull
    private String teacherId;
    private String note;
    private Integer statusId;
    private Integer pertemuanKe;

    private List<PresenceKbmVMStudent> students = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getPresenceDate() {
        return presenceDate;
    }

    public void setPresenceDate(Instant presenceDate) {
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

    public Integer getPertemuanKe() {
        return pertemuanKe;
    }

    public void setPertemuanKe(Integer pertemuanKe) {
        this.pertemuanKe = pertemuanKe;
    }
}
