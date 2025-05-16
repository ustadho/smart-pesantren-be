package id.smartpesantren.service.dto;

import java.math.BigDecimal;

public class KBMAssesmentVM {
    private String id;
    private String classRoomId;
    private Integer semester;;
    private String studentId;
    private String studentName;
    private String studentNis;
    private String studentNisn;
    private String subjectId;
    private String subjectName;
    private BigDecimal nilaiHarian;
    private BigDecimal nilaiKetrampilan;
    private BigDecimal nilaiSikap;
    private BigDecimal nilaiPts;
    private BigDecimal nilaiPas;
    private BigDecimal nilaiAkhir;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(String classRoomId) {
        this.classRoomId = classRoomId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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

    public BigDecimal getNilaiHarian() {
        return nilaiHarian;
    }

    public void setNilaiHarian(BigDecimal nilaiHarian) {
        this.nilaiHarian = nilaiHarian;
    }

    public BigDecimal getNilaiKetrampilan() {
        return nilaiKetrampilan;
    }

    public void setNilaiKetrampilan(BigDecimal nilaiKetrampilan) {
        this.nilaiKetrampilan = nilaiKetrampilan;
    }

    public BigDecimal getNilaiSikap() {
        return nilaiSikap;
    }

    public void setNilaiSikap(BigDecimal nilaiSikap) {
        this.nilaiSikap = nilaiSikap;
    }

    public BigDecimal getNilaiPts() {
        return nilaiPts;
    }

    public void setNilaiPts(BigDecimal nilaiPts) {
        this.nilaiPts = nilaiPts;
    }

    public BigDecimal getNilaiPas() {
        return nilaiPas;
    }

    public void setNilaiPas(BigDecimal nilaiPas) {
        this.nilaiPas = nilaiPas;
    }

    public BigDecimal getNilaiAkhir() {
        return nilaiAkhir;
    }

    public void setNilaiAkhir(BigDecimal nilaiAkhir) {
        this.nilaiAkhir = nilaiAkhir;
    }
}
