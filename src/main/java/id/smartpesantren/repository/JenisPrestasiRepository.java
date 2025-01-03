package id.smartpesantren.repository;

import id.smartpesantren.dto.JenisPrestasiDTO;
import id.smartpesantren.entity.JenisPrestasi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JenisPrestasiRepository extends JpaRepository<JenisPrestasi, String> {
    @Query("select new id.smartpesantren.dto.JenisPrestasiDTO(a) " +
            "from JenisPrestasi a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    public Page<JenisPrestasiDTO> filter(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.JenisPrestasiDTO(a) " +
            "from JenisPrestasi a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q \n" +
            "and coalesce(a.active, false) = true")
    public Iterable<JenisPrestasiDTO> filterAll(@Param("q") String q);
}
