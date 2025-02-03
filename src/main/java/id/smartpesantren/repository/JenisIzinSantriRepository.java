package id.smartpesantren.repository;

import id.smartpesantren.dto.JenisIzinSantriDTO;
import id.smartpesantren.entity.JenisIzinSantri;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JenisIzinSantriRepository extends JpaRepository<JenisIzinSantri, String> {
    @Query("select new id.smartpesantren.dto.JenisIzinSantriDTO(a) " +
            "from JenisIzinSantri a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    public Page<JenisIzinSantriDTO> filter(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.JenisIzinSantriDTO(a) " +
            "from JenisIzinSantri a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q \n" +
            "and coalesce(a.active, false) = true")
    public Iterable<JenisIzinSantriDTO> filterAll(@Param("q") String q);
}
