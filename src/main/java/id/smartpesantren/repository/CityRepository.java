package id.smartpesantren.repository;

import id.smartpesantren.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    @Query("from City c " +
            "where upper(c.code) like :q or upper(c.name) like :q ")
    Page<City> filterCity(@Param("q") String q, Pageable p);

    @Query("from City c " +
            "where c.province.id=:pid \n" +
            "and upper(c.code) like :q or upper(c.name) like :q ")
    List<City> findAllCity(@Param("pid") Integer pid, @Param("q") String q, Pageable p);

    Optional<City> findByCode(String code);
}
