package id.smartpesantren.repository;

import id.smartpesantren.dto.SubjectCategoryDTO;
import id.smartpesantren.entity.SubjectCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectCategoryRepository extends JpaRepository<SubjectCategory, String> {
    @Query("select new id.smartpesantren.dto.SubjectCategoryDTO(a) " +
            "from SubjectCategory a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    public Page<SubjectCategoryDTO> filter(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.SubjectCategoryDTO(a) " +
            "from SubjectCategory a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    public Iterable<SubjectCategoryDTO> filterAll(@Param("q") String q);
}
