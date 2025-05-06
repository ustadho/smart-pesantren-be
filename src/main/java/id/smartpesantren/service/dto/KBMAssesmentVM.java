package id.smartpesantren.service.dto;

import java.math.BigDecimal;

public class KBMAssesmentVM {
    private String id;
    private String academicYearId;
    private Integer semester;;
    private String studentId;
    private String studentName;
    private String studentNis;
    private String studentNisn;
    private BigDecimal nilaiTugas;
    private BigDecimal nilaiUts;
    private BigDecimal nilaiUas;
    private BigDecimal nilaiAkhir;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(String academicYearId) {
        this.academicYearId = academicYearId;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNis() {
        return studentNis;
    }

    public void setStudentNis(String studentNis) {
        this.studentNis = studentNis;
    }

    public String getStudentNisn() {
        return studentNisn;
    }

    public void setStudentNisn(String studentNisn) {
        this.studentNisn = studentNisn;
    }

    public BigDecimal getNilaiTugas() {
        return nilaiTugas;
    }

    public void setNilaiTugas(BigDecimal nilaiTugas) {
        this.nilaiTugas = nilaiTugas;
    }

    public BigDecimal getNilaiUts() {
        return nilaiUts;
    }

    public void setNilaiUts(BigDecimal nilaiUts) {
        this.nilaiUts = nilaiUts;
    }

    public BigDecimal getNilaiUas() {
        return nilaiUas;
    }

    public void setNilaiUas(BigDecimal nilaiUas) {
        this.nilaiUas = nilaiUas;
    }

    public BigDecimal getNilaiAkhir() {
        return nilaiAkhir;
    }

    public void setNilaiAkhir(BigDecimal nilaiAkhir) {
        this.nilaiAkhir = nilaiAkhir;
    }
}
