package id.smartpesantren.repository;

import id.smartpesantren.dto.JenisKegiatanDTO;
import id.smartpesantren.entity.JenisKegiatan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JenisKegiatanRepository extends JpaRepository<JenisKegiatan, String> {
    @Query("select new id.smartpesantren.dto.JenisKegiatanDTO(a) " +
            "from JenisKegiatan a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    public Page<JenisKegiatanDTO> filter(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.JenisKegiatanDTO(a) " +
            "from JenisKegiatan a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q \n" +
            "and coalesce(a.active, false) = true")
    public Iterable<JenisKegiatanDTO> filterAll(@Param("q") String q);
}
