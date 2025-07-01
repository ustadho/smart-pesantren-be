package id.smartpesantren.repository;

import id.smartpesantren.entity.PresensiHalaqohSantri;
import id.smartpesantren.service.dto.PresensiHalaqohSantriQuery;
import id.smartpesantren.service.dto.StudentListQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PresensiHalaqohSantriRepository extends JpaRepository<PresensiHalaqohSantri, String> {
    @Query(value = "with s as (\n" +
            "\tSELECT hs.student_id, s.name , s.nis, coalesce(cr.name,'') class_room, s.photo\n" +
            "\tFROM halaqoh_student hs \n" +
            "\tjoin ac_student s on s.id=hs.student_id \n" +
            "\tleft join ac_class_room cr on cr.id=s.class_room_id \n" +
            "\twhere hs.halaqoh_id = (select halaqoh_id from presensi_halaqoh ph where ph.id=:pesenceHalaqohId)\n" +
            "\torder by s.name\n" +
            "), p as (\n" +
            "\tselect phs.presensi_halaqoh_id, phs.santri_id, phs.id, phs.status_id, coalesce(ps.name,'') status_name, ps.\"name\", coalesce(phs.catatan,'') catatan \n" +
            "\tfrom presensi_halaqoh ph\n" +
            "\tjoin presensi_halaqoh_santri phs on ph.id = phs.presensi_halaqoh_id \n" +
            "\tJOIN presence_status ps on ps.id=coalesce(phs.status_id, 1)\n" +
            "\twhere ph.id = :pesenceHalaqohId\n" +
            ")\n" +
            "select s.student_id \"santriId\", s.name \"santriName\", s.nis \"santriNis\", s.class_room  \"classRoom\", s.photo, \n" +
            "p.presensi_halaqoh_id \"presensiHalaqohId\", p.id, coalesce(p.status_id, 1) \"statusId\", coalesce(p.status_name,'Hadir') \"statusName\"\n" +
            "from s\n" +
            "left join p on s.student_id=p.santri_id\n" +
            "order by s.name ", nativeQuery = true)
    public List<PresensiHalaqohSantriQuery> findSantriByPresensiHalaqohId(@Param("pesenceHalaqohId") String pesenceHalaqohId);
}
