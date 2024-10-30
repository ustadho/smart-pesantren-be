package id.smartpesantren.repository;

import id.smartpesantren.dto.EmployeeCategoryDTO;
import id.smartpesantren.dto.SectionDTO;
import id.smartpesantren.entity.EmployeeCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeCategoryRepository extends JpaRepository<EmployeeCategory, String> {
    @Query("select new id.smartpesantren.dto.EmployeeCategoryDTO(a) " +
            "from EmployeeCategory a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    public Page<EmployeeCategoryDTO> filter(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.EmployeeCategoryDTO(a) " +
            "from EmployeeCategory a " +
            "order by a.code")
    public Iterable<EmployeeCategoryDTO> filterAll(@Param("q") String q);
}
