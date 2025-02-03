package id.smartpesantren.repository;

import id.smartpesantren.dto.JenisPelanggaranDTO;
import id.smartpesantren.entity.JenisPelanggaran;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JenisPelanggaranRepository extends JpaRepository<JenisPelanggaran, String> {
    @Query("select new id.smartpesantren.dto.JenisPelanggaranDTO(a) " +
            "from JenisPelanggaran a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    public Page<JenisPelanggaranDTO> filter(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.JenisPelanggaranDTO(a) " +
            "from JenisPelanggaran a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q \n" +
            "and coalesce(a.active, false) = true")
    public Iterable<JenisPelanggaranDTO> filterAll(@Param("q") String q);
}
