package id.smartpesantren.dto;

import java.util.Date;

public interface PresenceSubjectStudentDTO {
    public String getStudentId();
    public String getNis();
    public String getNisn();
    public String getStudentName();
    public String getPresenceId();
    public Integer getPresenceStatusId();
    public String getPresenceStatusName();
    public String getNote();
    public String getAttachment();
    public Date getPresenceDate();
}
