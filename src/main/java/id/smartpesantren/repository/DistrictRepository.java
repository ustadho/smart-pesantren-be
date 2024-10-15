package id.smartpesantren.repository;

import id.smartpesantren.entity.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query("from District c " +
            "where upper(c.code) like :q or upper(c.name) like :q ")
    Page<District> filterDistrict(@Param("q") String q, Pageable p);

    @Query("from District c " +
            "where (coalesce(:pid,0)=0 OR c.city.id=:pid) \n" +
            "and upper(c.code) like :q or upper(c.name) like :q ")
    List<District> findAllDistrict(@Param("pid") Integer pid, @Param("q") String q, Pageable p);

    Optional<District> findByCode(String code);
}
