package id.smartpesantren.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ac_setting_schedule_teacher")
public class SettingScheduleTeacher extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "setting_id", nullable = false)
    @JsonBackReference
    private SettingSchedule setting;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private PersonData teacher;

}
