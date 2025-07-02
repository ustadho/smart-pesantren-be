package id.smartpesantren.repository;

import id.smartpesantren.dto.HalaqohDTO;
import id.smartpesantren.dto.HalaqohLookupQuery;
import id.smartpesantren.entity.*;
import id.smartpesantren.service.dto.StudentListQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HalaqohRepository extends JpaRepository<Halaqoh, String> {
    @Query(value = "select a.description \n" +
            "from halaqoh_student s " +
            "join halaqoh a on a.id=s.halaqoh_id\n" +
            "where s.student_id=:studentId \n " +
            "and a.academic_year_id=:academicYearId limit 1", nativeQuery = true)
    public String findByStudentAndAcademicYear(@Param("studentId") String studentId, @Param("academicYearId") String academicYearId);

    public Halaqoh findTop1ByPesantrenAndAcademicYear(Pesantren pesantren, AcademicYear academicYear);

    @Query("select new id.smartpesantren.dto.HalaqohDTO(a) \n " +
            "from Halaqoh a " +
            "where (coalesce(:academicYear,'')='' OR a.academicYear.id=:academicYear)\n" +
            "AND (coalesce(:pesantren,'')='' OR a.pesantren.id=:pesantren)")
    Page<HalaqohDTO> filter(@Param("academicYear") String academicYear,
                            @Param("pesantren") String pesantren,
                            Pageable pageable);

    @Query("select a from Halaqoh a \n" +
            "left join fetch a.musyrifs m \n" +
            "where a.id=:id")
    Optional<Halaqoh> findByHalaqohId(@Param("id") String id);

    @Query(value = "select hm.halaqoh_id  \n" +
            "from halaqoh_musyrif hm \n" +
            "join halaqoh h on h.id=hm.halaqoh_id \n" +
            "where hm.musyrif_id = ?1\n" +
            "and h.academic_year_id = (select id from academic_year ay where ay.is_default = true order by end_date desc limit 1)",
    nativeQuery = true)
    String findActiveHalaqohIdByMusyrifId(String personId);

    @Query(value = "select count(1)\n" +
            "from halaqoh_musyrif hm \n" +
            "join halaqoh h on h.id=hm.halaqoh_id \n" +
            "where hm.musyrif_id = :musyrifId\n" +
            "and h.academic_year_id = :academicYearId \n" +
            "and h.id != :halaqohId", nativeQuery = true)
    public Integer checkExistsMusyrifAndAcademicYear(@Param("musyrifId") String musyrifId, @Param("academicYearId") String academicYearId, @Param("halaqohId") String halaqohId);

    @Query(value = "with hs as (\n" +
            "\tselect hs.halaqoh_id, count(hs.id) student_count\n" +
            "\tfrom halaqoh h\n" +
            "\tjoin halaqoh_student hs on hs.halaqoh_id=h.id\n" +
            "\tjoin c_pesantren cp  on cp.id=h.pesantren_id \n" +
            "\twhere (coalesce(?1, '')='' OR cp.sex=?1  )\n" +
            "and h.academic_year_id = (select id from academic_year ay where ay.is_default = true order by end_date desc limit 1)\n" +
            "\tgroup by hs.halaqoh_id\n" +
            ") \n" +
            "\n" +
            "select hm.halaqoh_id \"halaqohId\",coalesce(h.description,'') \"halaqohDesc\", hm.musyrif_id \"pembimbingId\", pd.name  \"pembimbingName\", coalesce(hs.student_count,0) \"studentCount\"\n" +
            "from halaqoh_musyrif hm \n" +
            "join halaqoh h on h.id=hm.halaqoh_id \n" +
            "join c_pesantren cp  on cp.id=h.pesantren_id \n" +
            "join person_data pd on pd.id=hm.musyrif_id \n" +
            "left join hs on hs.halaqoh_id=h.id\n" +
            "where (coalesce(?1, '')='' OR cp.sex=?1 ) \n " +
            "and h.academic_year_id = (select id from academic_year ay where ay.is_default = true order by end_date desc limit 1)\n" +
            "order by pd.name", nativeQuery = true)
    public List<HalaqohLookupQuery> lookup(String sex);

    @Query(value = "SELECT hs.student_id id, s.name , s.nis, s.nisn, s.dob, coalesce(cr.name,'') \"classRoom\", ay.name \"joinYear\", s.photo\n" +
            "FROM halaqoh_student hs \n" +
            "join ac_student s on s.id=hs.student_id \n" +
            "left join ac_class_room cr on cr.id=s.class_room_id \n" +
            "left join academic_year ay on ay.id=s.join_year_id \n" +
            "where hs.halaqoh_id = :halaqohId\n" +
            "order by s.name", nativeQuery = true)
    public List<StudentListQuery> findStudentByHalaqohId(@Param("halaqohId") String halaqohId);

}
