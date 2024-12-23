package id.smartpesantren.web.rest.vm;

import javax.validation.constraints.NotNull;

public class SubjectScheduleVM {
    private String id;
    @NotNull
    private String classRoomId;
    @NotNull
    private Integer dayId;
    private Integer dayName;
    @NotNull
    private String subjectId;
    private String subjectName;
    @NotNull
    private String activityTimeId;
    private String activityTimeName;
    @NotNull
    private String teacherId;
    private String teacherName;

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

    public Integer getDayName() {
        return dayName;
    }

    public void setDayName(Integer dayName) {
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

    public String getActivityTimeId() {
        return activityTimeId;
    }

    public void setActivityTimeId(String activityTimeId) {
        this.activityTimeId = activityTimeId;
    }

    public String getActivityTimeName() {
        return activityTimeName;
    }

    public void setActivityTimeName(String activityTimeName) {
        this.activityTimeName = activityTimeName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
