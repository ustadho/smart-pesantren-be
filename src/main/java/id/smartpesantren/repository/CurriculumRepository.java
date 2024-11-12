package id.smartpesantren.repository;

import id.smartpesantren.dto.CurriculumDTO;
import id.smartpesantren.entity.Curriculum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CurriculumRepository extends JpaRepository<Curriculum, String> {
    @Query("select new id.smartpesantren.dto.CurriculumDTO(c) " +
            "from Curriculum c \n" +
            "where c.foundation.id=?#{principal.foundationId} \n" +
            "and upper(c.name) like :q")
    public Page<CurriculumDTO> filter(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.CurriculumDTO(c) " +
            "from Curriculum c \n" +
            "where c.foundation.id=?#{principal.foundationId} \n" +
            "and upper(c.name) like :q")
    public Iterable<CurriculumDTO> findAll(@Param("q") String q);
}
