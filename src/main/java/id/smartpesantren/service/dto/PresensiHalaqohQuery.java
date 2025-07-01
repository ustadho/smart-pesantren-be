package id.smartpesantren.service.dto;

import java.time.Instant;
import java.util.Date;

public interface PresensiHalaqohQuery {
    String getHalaqohId();
    Date getTanggal();
    String getHalaqohName();
    String getPembimbingId();
    String getPembimbingName();
    Integer getTahfidzTimeId();
    String getTahfidzTimeName();
    String getStartTime();
    String getEndTime();
    String getPresenceId();
    Instant getPresenceDate();
    String getPresenceNote();
    String getPresenceAttachment();
    Integer getPresenceStatusId();
    String getPresenceStatusName();
    Integer getJumlahSantri();
}
