package id.smartpesantren.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface ScheduleTeacherListQuery {
    public String getId();
    public LocalDate getScheduleDate();
    public Integer getDayId();
    public String getSubjectName();
    public String getTimeSeq();
    public String getStartTime();
    public String getEndTime();
    public String getInstitutionName();
    public String getClassRoom();
    public String getSex();
    public Instant getPresenceDate();
}
