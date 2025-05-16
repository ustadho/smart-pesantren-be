package id.smartpesantren.dto;

import id.smartpesantren.entity.SettingPenilaian;

import java.math.BigDecimal;

public class SettingPenilaianVM {
    private String id;
    private String institutionId;
    private String getInstitutionName;
    private BigDecimal persenHarian;
    private BigDecimal persenKetrampilan;
    private BigDecimal persenSikap;
    private BigDecimal persenPts;
    private BigDecimal persenPas;

    public SettingPenilaianVM() {
    }

    public SettingPenilaianVM(SettingPenilaian s) {
        setId(s.getId());
        setInstitutionId(s.getInstitution().getId());
        setGetInstitutionName(s.getInstitution().getName());
        setPersenHarian(s.getPersenHarian());
        setPersenKetrampilan(s.getPersenKetrampilan());
        setPersenSikap(s.getPersenSikap());
        setPersenPts(s.getPersenPts());
        setPersenPas(s.getPersenPas());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getGetInstitutionName() {
        return getInstitutionName;
    }

    public void setGetInstitutionName(String getInstitutionName) {
        this.getInstitutionName = getInstitutionName;
    }

    public BigDecimal getPersenHarian() {
        return persenHarian;
    }

    public void setPersenHarian(BigDecimal persenHarian) {
        this.persenHarian = persenHarian;
    }

    public BigDecimal getPersenKetrampilan() {
        return persenKetrampilan;
    }

    public void setPersenKetrampilan(BigDecimal persenKetrampilan) {
        this.persenKetrampilan = persenKetrampilan;
    }

    public BigDecimal getPersenSikap() {
        return persenSikap;
    }

    public void setPersenSikap(BigDecimal persenSikap) {
        this.persenSikap = persenSikap;
    }

    public BigDecimal getPersenPts() {
        return persenPts;
    }

    public void setPersenPts(BigDecimal persenPts) {
        this.persenPts = persenPts;
    }

    public BigDecimal getPersenPas() {
        return persenPas;
    }

    public void setPersenPas(BigDecimal persenPas) {
        this.persenPas = persenPas;
    }
}
