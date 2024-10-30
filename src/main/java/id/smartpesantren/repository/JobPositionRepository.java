package id.smartpesantren.repository;

import id.smartpesantren.dto.JobPositionDTO;
import id.smartpesantren.entity.JobPosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JobPositionRepository extends JpaRepository<JobPosition, String> {
    @Query("select new id.smartpesantren.dto.JobPositionDTO(c.id, c.code, c.name, c.description, l.id, l.name, l.level, c.active) " +
            "from JobPosition c \n" +
            "join c.jobLevel l  \n" +
            "where c.foundation.id=?#{principal.foundationId} \n" +
            "and upper(c.name) like :q")
    public Page<JobPositionDTO> filterJobPosition(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.JobPositionDTO(c.id, c.code, c.name, c.description, l.id, l.name, l.level, c.active) " +
            "from JobPosition c \n" +
            "left join c.jobLevel l  \n" +
            "where c.foundation.id=?#{principal.foundationId} \n" +
            "and upper(c.name) like :q")
    public Iterable<JobPositionDTO> findAllJobPosition(@Param("q") String q);

    @Query("from JobPosition j " +
            "where j.code=:code " +
            "and j.foundation.id=?#{principal.foundationId} ")
    public Optional<JobPosition> findByCode(@Param("code") String code);
}
