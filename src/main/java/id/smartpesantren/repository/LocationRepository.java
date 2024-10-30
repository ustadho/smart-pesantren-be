package id.smartpesantren.repository;

import id.smartpesantren.dto.LocationDTO;
import id.smartpesantren.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, String> {
    @Query("select new id.smartpesantren.dto.LocationDTO(c.id, c.code, c.name, c.description, c.latLong) " +
            "from Location c \n" +
            "where c.foundation.id=?#{principal.foundationId} \n" +
            "and upper(c.name) like :q")
    public Page<LocationDTO> filter(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.dto.LocationDTO(c.id, c.code, c.name, c.description, c.latLong) " +
            "from Location c \n" +
            "where c.foundation.id=?#{principal.foundationId} \n" +
            "and upper(c.name) like :q \n" +
            "order by c.name")
    public Iterable<LocationDTO> findAllLocation(@Param("q") String q);
}
