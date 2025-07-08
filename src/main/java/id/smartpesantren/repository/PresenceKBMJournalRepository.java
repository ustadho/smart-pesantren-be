package id.smartpesantren.repository;

import id.smartpesantren.dto.PresenceKbmJurnalHistoryDTO;
import id.smartpesantren.entity.PresenceKBMJurnal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PresenceKBMJournalRepository extends JpaRepository<PresenceKBMJurnal, String> {
    @Query("from PresenceKBMJurnal r " +
            "where r.presenceKBM.id=?1")
    Optional<PresenceKBMJurnal> findByPresenceId(String id);

    @Query(value = "select j.id, p.presence_date \"presenceDate\" , coalesce(p.pertemuan_ke,0) \"pertemuanKe\", coalesce(j.materi_pokok, '') \"materiPokok\", coalesce(j.kegiatan,'') \"kegiatan\", coalesce(j.penilaian,'') \"penilaian\", \n" +
            "j.created_date \"createdDate\"\n" +
            "from ac_presence_kbm_jurnal j \n" +
            "join ac_presence_kbm p on p.id=j.presence_kbm_id \n" +
            "where p.schedule_id = ?\n" +
            "order by p.presence_date desc, p.created_date desc", nativeQuery = true)
    public List<PresenceKbmJurnalHistoryDTO> findHistoryByScheduleTeacherId(String id);
}
