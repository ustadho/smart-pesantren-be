package id.smartpesantren.repository;

import id.smartpesantren.dto.JobLevelDTO;
import id.smartpesantren.entity.JobLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobLevelRepository extends JpaRepository<JobLevel, String> {
    @Query("select new id.smartpesantren.dto.JobLevelDTO(c.id, c.code, c.name, c.description, c.level, c.color) " +
            "from JobLevel c \n" +
            "where c.foundation.id=?#{principal.foundationId} \n" +
            "and upper(c.name) like :q")
    public Page<JobLevelDTO> filterJobLevel(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.JobLevelDTO(c.id, c.code, c.name, c.description, c.level, c.color) " +
            "from JobLevel c \n" +
            "where c.foundation.id=?#{principal.foundationId} \n" +
            "and upper(c.name) like :q")
    public Iterable<JobLevelDTO> findAllJobLevel(@Param("q") String q);
}
