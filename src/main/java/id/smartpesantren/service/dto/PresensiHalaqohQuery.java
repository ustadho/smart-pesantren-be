package id.smartpesantren.service.dto;

import java.util.Date;

public interface PresensiHalaqohQuery {
    String getHalaqohId();
    String getHalaqohName();
    String getPembimbingId();
    String getPembimbingName();
    Integer getTahfidzTimeId();
    String getTahfidzTimeName();
    String getStartTime();
    String getEndTime();
    String getPresenceId();
    Date getPresenceDate();
    String getPresenceNote();
    String getPresenceAttachment();
    Integer getPresenceStatusId();
    String getPresenceStatusName();
}
