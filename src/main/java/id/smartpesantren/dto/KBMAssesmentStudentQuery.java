package id.smartpesantren.dto;

import java.math.BigDecimal;

public interface KBMAssesmentStudentQuery {
    public String getInstitutionId();
    public String getStudentId();
    public String getStudentName();
    public String getSubjectId();
    public String getSubjectName();
    public String getStudentNis();
    public String getId();
    public BigDecimal getNilaiHarian();
    public BigDecimal getNilaiKetrampilan();
    public BigDecimal getNilaiSikap();
    public BigDecimal getNilaiPts();
    public BigDecimal getNilaiPas();
    public BigDecimal getNilaiAkhir();
}
