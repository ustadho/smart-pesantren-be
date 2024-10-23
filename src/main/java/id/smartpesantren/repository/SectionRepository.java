package id.smartpesantren.repository;

import id.smartpesantren.dto.SectionDTO;
import id.smartpesantren.entity.Section;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SectionRepository extends JpaRepository<Section, String> {
    @Query("select new id.smartpesantren.dto.SectionDTO(d) " +
            "from Section d \n" +
            "where d.foundation.id=?#{principal.foundationId} \n" +
            "and upper(d.name) like :q")
    public Page<SectionDTO> filter(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.SectionDTO(d) " +
            "from Section d \n" +
            "where d.foundation.id=?#{principal.foundationId} \n" +
            "and upper(d.name) like :q")
    public Iterable<SectionDTO> retrieveAll(@Param("q") String q);
}
