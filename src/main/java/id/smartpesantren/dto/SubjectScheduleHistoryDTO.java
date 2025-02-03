package id.smartpesantren.dto;

import org.apache.poi.util.ShortField;

import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetTime;

public interface SubjectScheduleHistoryDTO {
    public String getId();
    public String getCreatedBy();
    public Instant getCreatedDate();
    public String getActivity();
    public String getDayName();
    public String getSubjectName();
    public String getTeacherName();
    public Short getSeq();
    public LocalTime getStartTime();
    public LocalTime getEndTime();
}
