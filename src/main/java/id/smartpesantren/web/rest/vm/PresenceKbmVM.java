package id.smartpesantren.web.rest.vm;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PresenceKbmVM {
    private String id;
    @NotNull
    private Date presenceDate;
    @NotNull
    private String subjectScheduleId;
    @NotNull
    private String teacherId;
    private String description;

    private List<PresenceKbmVMStudent> students = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPresenceDate() {
        return presenceDate;
    }

    public void setPresenceDate(Date presenceDate) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PresenceKbmVMStudent> getStudents() {
        return students;
    }

    public void setStudents(List<PresenceKbmVMStudent> students) {
        this.students = students;
    }
}
