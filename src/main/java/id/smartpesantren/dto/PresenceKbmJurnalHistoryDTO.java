package id.smartpesantren.dto;

import java.time.Instant;
import java.time.LocalDate;

public interface PresenceKbmJurnalHistoryDTO {
    String getId();
    LocalDate getPresenceDate();
    Integer getPertemuanKe();
    String getMateriPokok();
    String getKegiatan();
    String getPenilaian();
    Instant getCreatedDate();
}
