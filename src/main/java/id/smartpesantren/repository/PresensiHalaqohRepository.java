package id.smartpesantren.repository;

import id.smartpesantren.entity.PresensiHalaqoh;
import id.smartpesantren.service.dto.PresensiHalaqohQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PresensiHalaqohRepository extends JpaRepository<PresensiHalaqoh, String> {
    @Query(value = "with a as (\n" +
            "\tSELECT h.id, h.description, hm.musyrif_id, pd.name\n" +
            "\tfrom halaqoh_musyrif hm \n" +
            "\tjoin halaqoh h on h.id=hm.halaqoh_id \n" +
            "\tjoin person_data pd on pd.id=hm.musyrif_id \n" +
            "\twhere h.id = :halaqohId\n" +
            "), p as (\n" +
            "\tselect tt.id tahfidz_time_id, tt.name, tt.start_time, tt.end_time, ph.id, ph.tanggal, ph.created_date, ph.catatan, ph.attachment, ph.presence_status_id, ps.name presence_status_name\n" +
            "\tfrom a \n" +
            "\tjoin tahfidz_time tt on true\n" +
            "\tleft join presensi_halaqoh ph on ph.halaqoh_id = a.id\n" +
            "\tand cast(ph.tanggal as date) = cast(:tanggal as date) and tt.id=ph.tahfidz_time_id\n" +
            "\tleft join presence_status ps on ps.id=ph.presence_status_id\n" +
            "\twhere tt.id = :tahfidzTimeId\n" +
            ")\n" +
            "select a.id \"halaqohId\", a.description \"halaqohName\", a.musyrif_id \"pembimbingId\", a.name \"pembimbingName\", \n" +
            "p.tahfidz_time_id \"tahfidzTimeId\", p.name \"tahfidzTimeName\", p.start_time \"startTime\", p.end_time \"endTime\",\n" +
            "p.id \"presenceId\", p.created_date \"presenceDate\", p.catatan \"presenceNote\", p.attachment \"presenceAttachment\", \n" +
            "p.presence_status_id \"presenceStatusId\", p.presence_status_name \"presenceStatusName\", \n" +
            "coalesce((select count(1) from halaqoh_student hs where halaqoh_id =:halaqohId),0) \"jumlahSantri\"  " +
            "from a, p ", nativeQuery = true)
    public PresensiHalaqohQuery findPresenceByHalaqohIdAndTahfidzTimeId(@Param("halaqohId") String halaqohId,
                                                                        @Param("tanggal") String tanggal,
                                                                        @Param("tahfidzTimeId") Integer tahfidzTimeId);
}
