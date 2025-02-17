package id.smartpesantren.dto;

public class ActivityScheduleByDayDTOSchedule {
    private String id;
    private String classRoomId;
    private String subjectId;
    private String subjectName;
    private String activityStartId;
    private String activityStartTime;
    private String activityEndId;
    private String activityEndTime;
    private Integer duration;
    private String teachers;

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

    public String getActivityStartId() {
        return activityStartId;
    }

    public void setActivityStartId(String activityStartId) {
        this.activityStartId = activityStartId;
    }

    public String getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(String activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public String getActivityEndId() {
        return activityEndId;
    }

    public void setActivityEndId(String activityEndId) {
        this.activityEndId = activityEndId;
    }

    public String getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(String activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }
}
