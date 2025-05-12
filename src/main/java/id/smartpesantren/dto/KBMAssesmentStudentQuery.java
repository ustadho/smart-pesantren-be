package id.smartpesantren.dto;

import java.math.BigDecimal;

public interface KBMAssesmentStudentQuery {
    public String getStudentId();
    public String getStudentName();
    public String getStudentNis();
    public String getId();
    public BigDecimal getNilaiTugas();
    public BigDecimal getNilaiUTS();
    public BigDecimal getNilaiUAS();
    public BigDecimal getNilaiAkhir();
}
