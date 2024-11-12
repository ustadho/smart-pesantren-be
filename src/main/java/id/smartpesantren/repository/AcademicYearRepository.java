package id.smartpesantren.repository;

import id.smartpesantren.dto.AcademicYearDTO;
import id.smartpesantren.entity.AcademicYear;
import id.smartpesantren.entity.Foundation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AcademicYearRepository extends JpaRepository<AcademicYear, String> {
    @Query("select new id.smartpesantren.dto.AcademicYearDTO(a) \n" +
            "from AcademicYear a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "order by a.name desc")
    public Iterable<AcademicYearDTO> findAllData();

    @Query("select new id.smartpesantren.dto.AcademicYearDTO(a) " +
            "from AcademicYear a " +
            "where a.foundation.id=?#{principal.foundationId} " +
            "and ((upper(a.name) like :q OR upper(coalesce(a.description,'')) like :q)) ")
    public Page<AcademicYearDTO> filter(@Param("q") String q, Pageable p);

    public Optional<AcademicYear> findByFoundationAndCode(Foundation foundation, String code);

    @Modifying
    @Query(value = "Update academic_year set is_default=false " +
            "where id != :id \n" +
            "and foundation_id=?#{principal.foundationId}", nativeQuery = true)
    void resetOtherDefault(@Param("id") String id);
}
