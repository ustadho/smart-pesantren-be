package id.smartpesantren.repository;

import id.smartpesantren.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query("from Country c " +
            "where upper(c.code) like :q or upper(c.name) like :q ")
    Page<Country> filterCountry(@Param("q") String q, Pageable p);

    @Query("from Country c " +
            "where upper(c.code) like :q or upper(c.name) like :q ")
    List<Country> findAllCountry(@Param("q") String q, Pageable p);

    Optional<Country> findByCode(String code);
}
