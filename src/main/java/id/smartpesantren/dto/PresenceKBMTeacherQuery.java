package id.smartpesantren.dto;

import java.time.Instant;
import java.time.LocalDate;

public interface PresenceKBMTeacherQuery {
    String getId();
    String getSubjectId();
    String getSubjectName();
    Integer getFromSeq();
    Integer getToSeq();
    String getStartTime();
    String getEndTime();
    String getInstitutionName();
    String getClassRoomId();
    String getClassRoomName();
    String getSex();
    String getPresenceId();
    Instant getPresenceDate();
    Integer getPresenceStatusId();
    String getPresenceStatusName();
    Integer getStudentCount();
    Integer getAlphaCount();
    Integer getIzinCount();
    Integer getSakitCount();
    Integer getPertemuanKe();
}
