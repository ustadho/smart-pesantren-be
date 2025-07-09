package id.smartpesantren.repository;

import id.smartpesantren.dto.StudentDTO;
import id.smartpesantren.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("select new id.smartpesantren.dto.StudentDTO(a.id, a.nis, a.nisn, a.name, " +
            "case when a.sex='M' then 'Putra' else 'Putri' end, a.dob, a.joinYear.code, " +
            "a.institution.name, a.category.name, " +
            "CASE WHEN a.status = '1' THEN 'Aktif' " +
            "   WHEN a.status='2' then 'Lulus' " +
            "   WHEN a.status='3' then 'Pindah' " +
            "ELSE 'Non Aktif' END, a.classRoom.name) \n " +
            "from Student a " +
            "where a.foundation.id=?#{principal.foundationId}\n" +
            "and (coalesce(:iid,'')='' OR a.institution.id=:iid) \n"+
            "and (coalesce(:academicYear,'')='' OR a.joinYear.id=:academicYear) \n"+
            "and (coalesce(:categoryId,'')='' OR a.category.id=:categoryId) \n"+
            "and (coalesce(:sex,'')='' OR a.sex=:sex) \n"+
            "and (coalesce(:cid,'')='' OR a.classRoom.id=:cid) \n"+
            "and (upper(coalesce(a.name,'')) like :q) \n"
    )
    public Page<StudentDTO> filter(@Param("q") String q,
                                   @Param("iid") String institutionId,
                                   @Param("academicYear") String academicYear,
                                   @Param("categoryId") String categoryId,
                                   @Param("sex") String sex,
                                   @Param("cid") String classRoomId,
                                   Pageable p);

    @Query("select new id.smartpesantren.dto.StudentDTO(a.id, a.nis, a.nisn, a.name, " +
            "case when a.sex='M' then 'Putra' else 'Putri' end, a.dob, a.joinYear.code, " +
            "a.institution.name, a.category.name, " +
            "CASE WHEN a.status = '1' THEN 'Aktif' " +
            "   WHEN a.status='2' then 'Lulus' " +
            "   WHEN a.status='3' then 'Pindah' " +
            "ELSE 'Non Aktif' END, a.classRoom.name) \n " +
            "from Student a " +
            "where a.foundation.id=?#{principal.foundationId}\n" +
            "and (coalesce(:iid,'')='' OR a.institution.id=:iid) \n"+
            "and (coalesce(:academicYear,'')='' OR a.joinYear.id=:academicYear) \n"+
            "and (coalesce(:categoryId,'')='' OR a.category.id=:categoryId) \n"+
            "and (coalesce(:sex,'')='' OR a.sex=:sex) \n"+
            "and (upper(coalesce(a.name,'')) like :q) \n"
    )
    public List<StudentDTO> filterAll(@Param("q") String q,
                                      @Param("iid") String institutionId,
                                      @Param("academicYear") String academicYear,
                                      @Param("categoryId") String categoryId,
                                      @Param("sex") String sex);

    @Modifying
    @Query(value = "update ac_student set tahfidz_target_id=?1\n" +
            "where id=?2", nativeQuery = true)
    public void updateTahfidzTarget(Integer tahfidzTargetId, String studentId);

    @Modifying
    @Query(value = "update ac_student set tahfidz_capaian_id=?1\n" +
            "where id=?2", nativeQuery = true)
    public void updateTahfidzCapaian(Integer tahfidzCapaianId, String studentId);

    @Query("from Student s \n" +
            "left join fetch s.father f " +
            "left join fetch s.mother m " +
            "left join fetch s.fatherGuardian fg " +
            "left join fetch s.motherGuardian mg " +
            "where s.id=?1")
    Optional<Student> findByStudentId(String id);
}
