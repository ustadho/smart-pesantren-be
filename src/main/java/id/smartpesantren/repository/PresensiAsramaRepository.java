package id.smartpesantren.repository;

import id.smartpesantren.dto.PresenceSubjectStudentDTO;
import id.smartpesantren.entity.PresensiAsrama;
import id.smartpesantren.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PresensiAsramaRepository extends JpaRepository<PresensiAsrama, String> {
    Optional<PresensiAsrama> findBySantriAndTanggalAndPresenceType(Student santri, @NotNull Date tanggal, String presenceType);

    @Query(value = "select ams.student_id \"studentId\", s.nis, s.nisn, s.name \"studentName\", " +
            "coalesce(pa.presence_status_id, 1) \"presenceStatusId\", coalesce(ps.name, 'HADIR') \"presenceStatusName\", pa.id \"presenceId\",  " +
            "pa.note, pa.attachment, coalesce(pa.tanggal, current_date) \"presenceDate\"\n" +
            "from asrama_mapping_student ams\n" +
            "join asrama_mapping am on am.id =ams.asrama_mapping_id \n" +
            "join ac_student s on s.id=ams.student_id \n" +
            "left join presensi_asrama pa on pa.santri_id = ams.student_id and pa.tanggal = current_date\n" +
            "left join presence_status ps on ps.id=pa.presence_status_id \n" +
            "where am.id = ?1\n" +
            "order by s.name, s.nis ", nativeQuery = true)
    public List<PresenceSubjectStudentDTO> findSantriByAsrama(String asramaId); //List<PresenceSubjectStudentDTO> findSantriByAsrama(@Param("asramaId") String asramaId)>
}
