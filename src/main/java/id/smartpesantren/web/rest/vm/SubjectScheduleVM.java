package id.smartpesantren.web.rest.vm;

import id.smartpesantren.dto.StudentDTO;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class SubjectScheduleVM {
    private String id;
    @NotNull
    private String classRoomId;
    @NotNull
    private Integer dayId;
    private String dayName;
    private String activityStartId;
    private String activityEndId;
    private Integer duration;
    private Set<SubjectScheduleVMSubjectTeacher> subjectTeachers;
    private Set<StudentDTO> students;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(String classRoomId) {
        this.classRoomId = classRoomId;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getActivityStartId() {
        return activityStartId;
    }

    public void setActivityStartId(String activityStartId) {
        this.activityStartId = activityStartId;
    }

    public String getActivityEndId() {
        return activityEndId;
    }

    public void setActivityEndId(String activityEndId) {
        this.activityEndId = activityEndId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<SubjectScheduleVMSubjectTeacher> getSubjectTeachers() {
        return subjectTeachers;
    }

    public void setSubjectTeachers(Set<SubjectScheduleVMSubjectTeacher> subjectTeachers) {
        this.subjectTeachers = subjectTeachers;
    }

    public Set<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentDTO> students) {
        this.students = students;
    }
}
