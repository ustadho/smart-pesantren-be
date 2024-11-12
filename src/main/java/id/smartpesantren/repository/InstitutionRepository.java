package id.smartpesantren.repository;

import id.smartpesantren.dto.InstitutionDTO;
import id.smartpesantren.dto.JobLevelDTO;
import id.smartpesantren.entity.Institution;
import id.smartpesantren.entity.JobLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InstitutionRepository extends JpaRepository<Institution, String> {
    @Query("select new id.smartpesantren.dto.InstitutionDTO(a) " +
            "from Institution a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    public Page<InstitutionDTO> filterJobLevel(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.InstitutionDTO(a) " +
            "from Institution a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    public Iterable<InstitutionDTO> findAllJobLevel(@Param("q") String q);
}
