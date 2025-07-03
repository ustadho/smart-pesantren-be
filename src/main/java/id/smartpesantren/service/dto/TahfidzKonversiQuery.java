package id.smartpesantren.service.dto;

import javax.persistence.Column;

public interface TahfidzKonversiQuery {
    public Integer getId();
    public Integer getJmlHalaman();
    public Integer getKonvJuz();
    public Integer getKonvHalaman();
    public Integer getAwalNoSurat();
    public String getAwalNamaSurat();
    public Integer getAwalAyatSurat();
    public Integer getAkhirNoSurat();
    public String getAkhirNamaSurat();
    public Integer getakhirAyatSurat();
}
