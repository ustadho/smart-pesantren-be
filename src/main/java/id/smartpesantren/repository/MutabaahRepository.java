package id.smartpesantren.repository;

import id.smartpesantren.dto.MutabaahHistoryQuery;
import id.smartpesantren.entity.Mutabaah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MutabaahRepository extends JpaRepository<Mutabaah, String> {
    @Query(value = "select m.id, m.santri_id \"santriId\", s.name \"santriName\", s.nis \"santriNis\", \n" +
            "coalesce(cr.name,'') \"classRoom\", m.tanggal, coalesce(tt.name,'') waktu, m.tipe, \n" +
            "m.dari_halaman_id \"dariHalamanId\", m.sampai_halaman_id \"sampaiHalamanId\", \n" +
            "coalesce(t2.jml_halaman,0)-coalesce(t1.jml_halaman,0)+1 \"jmlHalaman\", \n" +
            "m.created_by \"createdBy\", m.created_date \"createdDate\"\n" +
            "from mutabaah m \n" +
            "join ac_student s on s.id=m.santri_id\n" +
            "left join tahfidz_time tt on tt.id=m.waktu_id\n" +
            "left join tahfidz_konversi t1 on t1.id=m.dari_halaman_id\n" +
            "left join tahfidz_konversi t2 on t2.id=m.sampai_halaman_id\n" +
            "left join ac_class_room cr on cr.id=s.class_room_id\n" +
            "where (coalesce(:santriId,'')='' OR m.santri_id=:santriId)\n" +
            "and (cast(:fromDate as date) is null OR m.tanggal >= :fromDate)\n" +
            "and (cast(:toDate as date) is null OR m.tanggal <= :toDate)\n" +
            "and (coalesce(:waktuId,0) = 0 or m.waktu_id=:waktuId)\n" +
            "and (coalesce(:tipe,'')='' or m.tipe=:tipe)\n" +
            "and (coalesce(:createdBy,'')='' or m.created_by=:createdBy)", nativeQuery = true)
    public List<MutabaahHistoryQuery> findHistory(
            @Param("santriId") String santriId,
            @Param("fromDate") Date fromDate,
            @Param("toDate") Date toDate,
            @Param("waktuId") Integer waktuId,
            @Param("tipe") String tipe,
            @Param("createdBy") String createdBy
            );
}
