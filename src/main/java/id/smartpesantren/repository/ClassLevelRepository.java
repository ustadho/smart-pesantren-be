package id.smartpesantren.repository;

import id.smartpesantren.dto.ClassLevelDTO;
import id.smartpesantren.entity.ClassLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClassLevelRepository extends JpaRepository<ClassLevel, String> {
    @Query("select new id.smartpesantren.dto.ClassLevelDTO(" +
            "a.id, a.level, a.description) " +
            "from ClassLevel a " +
            "order by a.level desc")
    public Iterable<ClassLevelDTO> findAllClass();

    @Query("select new id.smartpesantren.dto.ClassLevelDTO(" +
            "a.id, a.level, a.description) " +
            "from ClassLevel a " +
            "where (upper(a.description) like :q) ")
    public Page<ClassLevelDTO> filter(@Param("q") String q, Pageable p);

    Optional<ClassLevel> findByLevel(Short level);
}

