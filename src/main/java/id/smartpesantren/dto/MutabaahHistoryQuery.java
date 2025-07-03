package id.smartpesantren.dto;

import java.time.Instant;
import java.util.Date;

public interface MutabaahHistoryQuery {
    public String getId();
    public String getSantriId();
    public String getSantriName();
    public String getSantriNis();
    public String getClassRoom();
    public Date getTanggal();
    public String getWaktu();
    public String getTipe();
    public Integer getDariHalamanId();
    public Integer getSampaiHalamanId();
    public Integer getJmlHalaman();
    public String getNilai();
    public String getCreatedBy();
    public Instant getCreatedDate();
}
