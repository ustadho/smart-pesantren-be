package id.smartpesantren.dto;

public class ActivityScheduleByDayDTO {
    private String dayId;
    private String dayName;
    private ActivityScheduleByDayDTOSchedule schedules;

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public ActivityScheduleByDayDTOSchedule getSchedules() {
        return schedules;
    }

    public void setSchedules(ActivityScheduleByDayDTOSchedule schedules) {
        this.schedules = schedules;
    }
}
