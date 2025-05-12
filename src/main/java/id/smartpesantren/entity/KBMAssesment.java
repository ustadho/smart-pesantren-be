package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "kbm_assesment")
public class KBMAssesment extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    @ManyToOne
    @JoinColumn(name = "class_room_id", nullable = false)
    ClassRoom classRoom;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    Subject subject;

    @Column(nullable = false)
    private Integer semester;;

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

    public Foundation getFoundation() {
        return foundation;
    }

    public void setFoundation(Foundation foundation) {
        this.foundation = foundation;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
