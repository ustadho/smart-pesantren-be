package id.smartpesantren.repository;

import id.smartpesantren.dto.TugasKepengasuhanDTO;
import id.smartpesantren.entity.TugasKepengasuhan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TugasKepengasuhanRepository extends JpaRepository<TugasKepengasuhan, String> {
    @Query("select new id.smartpesantren.dto.TugasKepengasuhanDTO(a) " +
            "from TugasKepengasuhan a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    public Page<TugasKepengasuhanDTO> filter(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.TugasKepengasuhanDTO(a) " +
            "from TugasKepengasuhan a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    public Iterable<TugasKepengasuhanDTO> filterAll(@Param("q") String q);
}
