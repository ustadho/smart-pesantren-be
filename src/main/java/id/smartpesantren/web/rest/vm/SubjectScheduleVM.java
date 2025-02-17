package id.smartpesantren.web.rest.vm;

import id.smartpesantren.dto.ActivityTimeDTO;
import id.smartpesantren.dto.EmployeeSimpleDTO;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class SubjectScheduleVM {
    private String id;
    @NotNull
    private String classRoomId;
    @NotNull
    private Integer dayId;
    private String dayName;
    @NotNull
    private String subjectId;
    private String subjectName;
    private String activityTimeStartId;
    private String activityTimeEndId;
    private Integer duration;
    private Set<EmployeeSimpleDTO> teachers;

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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getActivityTimeStartId() {
        return activityTimeStartId;
    }

    public void setActivityTimeStartId(String activityTimeStartId) {
        this.activityTimeStartId = activityTimeStartId;
    }

    public String getActivityTimeEndId() {
        return activityTimeEndId;
    }

    public void setActivityTimeEndId(String activityTimeEndId) {
        this.activityTimeEndId = activityTimeEndId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<EmployeeSimpleDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<EmployeeSimpleDTO> teachers) {
        this.teachers = teachers;
    }

}
