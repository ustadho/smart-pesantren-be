package id.smartpesantren.dto;

import java.util.List;

public interface MyScheduleWeeklyDTO {
    public String getScheduleId();
    public int getDayId();
    public String getDayName();
    public String getClassRoomName();
    public String getSubjectId();
    public String getSubjectName();
    public String getStartTime();
    public String getEndTime();
    public int getDuration();
    public String getTeachers();

}