package id.smartpesantren.repository;

import id.smartpesantren.dto.ClassLevelDTO;
import id.smartpesantren.entity.ClassLevel;
import id.smartpesantren.entity.Foundation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClassLevelRepository extends JpaRepository<ClassLevel, String> {
    @Query("select new id.smartpesantren.dto.ClassLevelDTO(a) " +
            "from ClassLevel a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "order by a.level desc")
    public Iterable<ClassLevelDTO> findAllClass();

    @Query("select new id.smartpesantren.dto.ClassLevelDTO(a) " +
            "from ClassLevel a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and (upper(coalesce(a.description,'')) like :q) ")
    public Page<ClassLevelDTO> filter(@Param("q") String q, Pageable p);

    Optional<ClassLevel> findByFoundationAndLevel(Foundation foundation, Short level);
}

