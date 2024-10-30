package id.smartpesantren.repository;

import id.smartpesantren.dto.ReferalInstitutionDTO;
import id.smartpesantren.entity.ReferalInstitution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReferalInstitutionRepository extends JpaRepository<ReferalInstitution, String> {
    @Query("select new id.smartpesantren.dto.ReferalInstitutionDTO(a) \n" +
           "from ReferalInstitution a \n" +
            "left join a.city c \n" +
            "where upper(a.name) like :q")
    public Page<ReferalInstitutionDTO> filter(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.ReferalInstitutionDTO(a) \n" +
            "from ReferalInstitution a \n" +
            "left join a.city c \n" +
            "where upper(a.name) like :q\n" +
            "order by a.name")
    public Iterable<ReferalInstitutionDTO> findAll(@Param("q") String q);

    Optional<ReferalInstitution> findByName(String name);
}
