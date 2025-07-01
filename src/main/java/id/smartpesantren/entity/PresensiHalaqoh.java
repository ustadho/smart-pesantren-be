package id.smartpesantren.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "presensi_halaqoh",
        uniqueConstraints = @UniqueConstraint(
        columnNames = {"halaqoh_id", "tanggal", "tahfidz_time_id"}
    )
)
public class PresensiHalaqoh extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "foundation_id", nullable = false)
    Foundation foundation;

    @Column(length = 2, columnDefinition = "varchar default 'PR'") // DEFAULT 'PR'
    private String presenceType; //PR: Presensi; PL: Kepulangan: DT: Kedatangan

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, columnDefinition = "timestamp without time zone")
    private Date tanggal;

    @ManyToOne
    @JoinColumn(name = "tahfidz_time_id", nullable = false)
    private TahfidzTime tahfidzTime;

    @ManyToOne
    @JoinColumn(name = "halaqoh_id", nullable = false)
    private Halaqoh halaqoh;

    @ManyToOne
    @JoinColumn(name = "pembimbing_id", nullable = false)
    private PersonData pembimbing;

    @ManyToOne
    @JoinColumn(name = "presence_status_id", nullable = false)
    PresenceStatus presenceStatus;

    @Column(columnDefinition = "text")
    private String catatan;
    private String attachment;

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

    public String getPresenceType() {
        return presenceType;
    }

    public void setPresenceType(String presenceType) {
        this.presenceType = presenceType;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public TahfidzTime getTahfidzTime() {
        return tahfidzTime;
    }

    public void setTahfidzTime(TahfidzTime tahfidzTime) {
        this.tahfidzTime = tahfidzTime;
    }

    public Halaqoh getHalaqoh() {
        return halaqoh;
    }

    public void setHalaqoh(Halaqoh halaqoh) {
        this.halaqoh = halaqoh;
    }

    public PersonData getPembimbing() {
        return pembimbing;
    }

    public void setPembimbing(PersonData pembimbing) {
        this.pembimbing = pembimbing;
    }

    public PresenceStatus getPresenceStatus() {
        return presenceStatus;
    }

    public void setPresenceStatus(PresenceStatus presenceStatus) {
        this.presenceStatus = presenceStatus;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

}
