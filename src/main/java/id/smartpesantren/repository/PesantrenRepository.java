package id.smartpesantren.repository;

import id.smartpesantren.dto.PesantrenDTO;
import id.smartpesantren.entity.Pesantren;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PesantrenRepository extends JpaRepository<Pesantren, String> {
    @Query("select new id.smartpesantren.dto.PesantrenDTO(a) " +
            "from Pesantren a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    public Page<PesantrenDTO> filterPesantren(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.PesantrenDTO(a) " +
            "from Pesantren a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    public Iterable<PesantrenDTO> findAllPesantren(@Param("q") String q);
}
