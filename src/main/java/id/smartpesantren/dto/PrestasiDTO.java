package id.smartpesantren.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.JenisPrestasi;
import id.smartpesantren.entity.Student;

import java.util.Date;

public class PrestasiDTO {
    private String id;
    private String jenisPrestasiId;
    private String jenisPrestasiName;
    private String studentId;
    private String studentName;
    private String studentNis;
    private String studentNisn;
    private String studentClassName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date tanggal;
    private String keterangan;
    private String attachment;

    public PrestasiDTO() {
    }

    public PrestasiDTO(String id, String jenisPrestasiId, String jenisPrestasiName, String studentId, String studentName, String studentNis, String studentNisn, String studentClassName, Date tanggal, String keterangan, String attachment) {
        this.id = id;
        this.jenisPrestasiId = jenisPrestasiId;
        this.jenisPrestasiName = jenisPrestasiName;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentNis = studentNis;
        this.studentNisn = studentNisn;
        this.studentClassName = studentClassName;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.attachment = attachment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJenisPrestasiId() {
        return jenisPrestasiId;
    }

    public void setJenisPrestasiId(String jenisPrestasiId) {
        this.jenisPrestasiId = jenisPrestasiId;
    }

    public String getJenisPrestasiName() {
        return jenisPrestasiName;
    }

    public void setJenisPrestasiName(String jenisPrestasiName) {
        this.jenisPrestasiName = jenisPrestasiName;
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

    public String getStudentClassName() {
        return studentClassName;
    }

    public void setStudentClassName(String studentClassName) {
        this.studentClassName = studentClassName;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
