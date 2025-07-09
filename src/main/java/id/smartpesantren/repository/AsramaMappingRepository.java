package id.smartpesantren.repository;

import id.smartpesantren.dto.AsramaMappingDTO;
import id.smartpesantren.dto.AsramaMappingStudentDTO;
import id.smartpesantren.dto.ClassRoomStudentDTO;
import id.smartpesantren.dto.SantriListDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.service.dto.AsramaMappingList;
import id.smartpesantren.web.rest.vm.AsramaMappingVM;
import id.smartpesantren.web.rest.vm.AsramaMappingVMStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AsramaMappingRepository extends JpaRepository<AsramaMapping, String> {
    @Query(value = "select a.name \n" +
            "from asrama_mapping_student ams " +
            "join asrama_mapping am on am.id=ams.asrama_mapping_id\n" +
            "join asrama a on a.id=am.asrama_id\n" +
            "where ams.student_id=:studentId \n " +
            "and am.academic_year_id=:academicYearId limit 1", nativeQuery = true)
    public String findByStudentAndAcademicYear(@Param("studentId") String studentId, @Param("academicYearId") String academicYearId);

    @Query("from AsramaMapping am " +
            "left join fetch am.musyrifs " +
            "where am.asrama=?1 and am.academicYear=?2")
    public AsramaMapping findTop1ByAsramaAndAcademicYear(Asrama asrama, AcademicYear academicYear);

    @Query("select new id.smartpesantren.dto.AsramaMappingDTO(a.id, ay.id, ay.code, am.id, am.name, b.name, p.name, am.capacity, p.sex, count(s)) \n " +
            "from AsramaMapping a \n" +
            "join a.academicYear ay \n" +
            "join a.asrama am \n" +
            "left join am.building b \n" +
            "left join am.pesantren p \n" +
            "left join a.students s \n" +
            "where (coalesce(:academicYear,'')='' OR ay.id=:academicYear)\n" +
            "AND (coalesce(:pesantren,'')='' OR p.id=:pesantren)\n" +
            "group by a.id, ay.id, ay.code, am.id, am.name, b.name, p.name, am.capacity, p.sex")
    Page<AsramaMappingDTO> filter(@Param("academicYear") String academicYear,
                                  @Param("pesantren") String pesantren,
                                  Pageable pageable);

    @Query("select a from AsramaMapping a \n" +
            "left join fetch a.musyrifs m \n" +
            "where a.id=:id")
    Optional<AsramaMapping> findByMappingId(@Param("id") String id);

    @Query(value = "select am.id, a.name, cp.name pesantren, count(distinct ams.id) \"jumlahSantri\", string_agg(distinct pd.name, ', ') as musyrifs \n" +
            "from asrama_mapping am \n" +
            "join asrama a on a.id=am.asrama_id \n" +
            "left join c_pesantren cp on a.pesantren_id=cp.id  \n" +
            "left join asrama_mapping_student ams on ams.asrama_mapping_id = am.id\n" +
            "left join asrama_mapping_musyrif amm on amm.asrama_mapping_id = am.id \n" +
            "left join person_data pd on pd.id=amm.musyrif_id \n" +
            "where am.academic_year_id =:academicYear\n" +
            "and (coalesce(:sex,'')='' OR cp.sex =:sex) \n" +
            "group by am.id, a.name, cp.name\n" +
            "order by a.name", nativeQuery = true)
    List<AsramaMappingList> findAllByAcademicYearAndSex(@Param("academicYear") String academicYear, @Param("sex") String sex);

    @Query("SELECT new id.smartpesantren.web.rest.vm.AsramaMappingVM(" +
            "am.id, " +
            "am.asrama.id, " +
            "am.asrama.name, " +
            "am.academicYear.id, " +
            "am.academicYear.name, " +
            "am.description) " +
            "FROM AsramaMapping am " +
            "WHERE am.id = :id")
    Optional<AsramaMappingVM> findAsramaMappingVMById(@Param("id") String id);

    @Query("SELECT new id.smartpesantren.web.rest.vm.AsramaMappingVMStudent(" +
            "ams.id, " +
            "s.id, " +
            "s.name, " +
            "s.nis, " +
            "s.nisn, " +
            "jy.name, " +
            "'', " +
            "ams.notes, " +
            "tt.id, " + // Asumsi targetTahfidz adalah relasi di AsramaMappingStudent
            "'Hal: '||tt.id ||' / Target: ' || tt.jmlHalaman || ' Hal. ('|| tt.konvJuz ||' Juz '||tt.konvHalaman ||' Hal.)') " + // Asumsi targetTahfidz adalah relasi di AsramaMappingStudent
            "FROM AsramaMappingStudent ams " +
            "JOIN ams.student s " +
            "JOIN s.joinYear jy " +
            "LEFT JOIN s.tahfidzTarget tt " +
            "WHERE ams.asramaMapping.id = :asramaMappingId")
    List<AsramaMappingVMStudent> findStudentsVMByAsramaMappingId(@Param("asramaMappingId") String asramaMappingId);

    @Query("SELECT pd.id FROM AsramaMapping am JOIN am.musyrifs pd WHERE am.id = :asramaMappingId")
    List<String> findMusyrifNamesByAsramaMappingId(@Param("asramaMappingId") String asramaMappingId);
}
