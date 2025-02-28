package id.smartpesantren.web.rest.vm;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class SubjectScheduleVM {
    private String id;
    @NotNull
    private String classRoomId;
    @NotNull
    private Integer dayId;
    private String dayName;
    private String activityTimeId;
    private String activityTimeStartId;
    private String activityTimeEndId;
    private Integer duration;
    private Set<SubjectScheduleVMSubjectTeacher> subjects;

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

    public String getActivityTimeId() {
        return activityTimeId;
    }

    public void setActivityTimeId(String activityTimeId) {
        this.activityTimeId = activityTimeId;
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

    public Set<SubjectScheduleVMSubjectTeacher> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectScheduleVMSubjectTeacher> subjects) {
        this.subjects = subjects;
    }

}
