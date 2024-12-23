package id.smartpesantren.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ActivityScheduleDTO {

    @JsonProperty("activityId")
    private String activityId;

    @JsonProperty("activityName")
    private String activityName;

    @JsonProperty("startTime")
    private String startTime;

    @JsonProperty("endTime")
    private String endTime;

    @JsonProperty("days")
    private List<DayScheduleDTO> days;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<DayScheduleDTO> getDays() {
        return days;
    }

    public void setDays(List<DayScheduleDTO> days) {
        this.days = days;
    }

    public static class DayScheduleDTO {
        @JsonProperty("dayId")
        private Integer dayId;

        @JsonProperty("dayName")
        private String dayName;

        @JsonProperty("id")
        private String id;

        @JsonProperty("subjectId")
        private String subjectId;

        @JsonProperty("subjectName")
        private String subjectName;

        @JsonProperty("teacherId")
        private String teacherId;

        @JsonProperty("teacherName")
        private String teacherName;

        // Getters and Setters


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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
}

