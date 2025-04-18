package id.smartpesantren.repository;

import id.smartpesantren.dto.StudentDTO;
import id.smartpesantren.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("select new id.smartpesantren.dto.StudentDTO(a.id, a.nis, a.nisn, a.name, " +
            "case when a.sex='M' then 'Putra' else 'Putri' end, a.dob, a.joinYear.code, " +
            "a.institution.name, a.category.name, " +
            "CASE WHEN a.status = '1' THEN 'Aktif' " +
            "   WHEN a.status='2' then 'Lulus' " +
            "   WHEN a.status='3' then 'Pindah' " +
            "ELSE 'Non Aktif' END) \n " +
            "from Student a " +
            "where a.foundation.id=?#{principal.foundationId}\n" +
            "and (coalesce(:iid,'')='' OR a.institution.id=:iid) \n"+
            "and (coalesce(:academicYear,'')='' OR a.joinYear.id=:academicYear) \n"+
            "and (coalesce(:categoryId,'')='' OR a.category.id=:categoryId) \n"+
            "and (coalesce(:sex,'')='' OR a.sex=:sex) \n"+
            "and (upper(coalesce(a.name,'')) like :q) \n"
    )
    public Page<StudentDTO> filter(@Param("q") String q,
                                   @Param("iid") String institutionId,
                                   @Param("academicYear") String academicYear,
                                   @Param("categoryId") String categoryId,
                                   @Param("sex") String sex,
                                   Pageable p);

    @Query("select new id.smartpesantren.dto.StudentDTO(a.id, a.nis, a.nisn, a.name, " +
            "case when a.sex='M' then 'Putra' else 'Putri' end, a.dob, a.joinYear.code, " +
            "a.institution.name, a.category.name, " +
            "CASE WHEN a.status = '1' THEN 'Aktif' " +
            "   WHEN a.status='2' then 'Lulus' " +
            "   WHEN a.status='3' then 'Pindah' " +
            "ELSE 'Non Aktif' END) \n " +
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
}
