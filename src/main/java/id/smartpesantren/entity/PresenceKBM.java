package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ac_presence_kbm")
public class PresenceKBM extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @Column(nullable = false, columnDefinition = "date default current_date")
    private LocalDate presenceDate;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    @JsonBackReference
    SubjectScheduleTeacher subjectScheduleTeacher;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private PersonData teacher;

    @ManyToOne
    @JoinColumn(name = "presence_status_id", nullable = false)
    PresenceStatus presenceStatus;

    @Column(columnDefinition = "text")
    private String note;

    @Column(columnDefinition = "text")
    private String kompetensiDasar;

    @Column(columnDefinition = "text")
    private String materi;

    @Column(columnDefinition = "text")
    private String indikator;

    private String fotoAbsen;

    private String pencapaian; //Tuntas

    private String attachment;

    @OneToMany(mappedBy = "presenceKBM", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<PresenceKBMStudent> students = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getPresenceDate() {
        return presenceDate;
    }

    public void setPresenceDate(LocalDate presenceDate) {
        this.presenceDate = presenceDate;
    }

    public SubjectScheduleTeacher getSubjectScheduleTeacher() {
        return subjectScheduleTeacher;
    }

    public void setSubjectScheduleTeacher(SubjectScheduleTeacher subjectScheduleTeacher) {
        this.subjectScheduleTeacher = subjectScheduleTeacher;
    }

    public PersonData getTeacher() {
        return teacher;
    }

    public void setTeacher(PersonData teacher) {
        this.teacher = teacher;
    }

    public PresenceStatus getPresenceStatus() {
        return presenceStatus;
    }

    public void setPresenceStatus(PresenceStatus presenceStatus) {
        this.presenceStatus = presenceStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<PresenceKBMStudent> getStudents() {
        return students;
    }

    public void setStudents(Set<PresenceKBMStudent> students) {
        this.students = students;
    }

    public String getKompetensiDasar() {
        return kompetensiDasar;
    }

    public void setKompetensiDasar(String kompetensiDasar) {
        this.kompetensiDasar = kompetensiDasar;
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
    }

    public String getIndikator() {
        return indikator;
    }

    public void setIndikator(String indikator) {
        this.indikator = indikator;
    }

    public String getFotoAbsen() {
        return fotoAbsen;
    }

    public void setFotoAbsen(String fotoAbsen) {
        this.fotoAbsen = fotoAbsen;
    }

    public String getPencapaian() {
        return pencapaian;
    }

    public void setPencapaian(String pencapaian) {
        this.pencapaian = pencapaian;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
