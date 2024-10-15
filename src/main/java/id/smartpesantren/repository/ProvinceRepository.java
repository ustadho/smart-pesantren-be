package id.smartpesantren.repository;

import id.smartpesantren.entity.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    @Query("from Province c " +
            "where upper(c.code) like :q or upper(c.name) like :q ")
    Page<Province> filterProvince(@Param("q") String q, Pageable p);

    @Query("from Province c " +
            "where c.country.id=:cid \n" +
            "and upper(c.code) like :q or upper(c.name) like :q ")
    List<Province> findAllProvince(@Param("cid") Integer cid, @Param("q") String q, Pageable p);

    Optional<Province> findByCode(String code);
}
