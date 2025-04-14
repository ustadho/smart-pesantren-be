package id.smartpesantren.dto;

import java.util.List;

public interface MyScheduleWeeklyDTO {
    public String getId();
    public String getScheduleId();
    public int getSeq();
    public int getDayId();
    public String getDayName();
    public String getClassRoomName();
    public String getInstitutionName();
    public String getSubjectId();
    public String getSubjectName();
    public String getStartTime();
    public String getEndTime();
    public int getDuration();
    public String getTeachers();

}