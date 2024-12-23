package id.smartpesantren.repository;

import id.smartpesantren.entity.Asrama;
import id.smartpesantren.entity.Building;
import id.smartpesantren.web.rest.vm.AsramaVM;
import id.smartpesantren.web.rest.vm.BuildingVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AsramaRepository extends JpaRepository<Asrama, String> {
    @Query("select new id.smartpesantren.web.rest.vm.AsramaVM( " +
            "a.id, a.code, a.name, a.description, b.id, b.name, l.name) " +
            "from Asrama a " +
            "left join a.building b " +
            "left join b.location l " +
            "where a.foundation.id = ?#{principal.foundationId} " +
            "and upper(a.name) like upper(:q) " +
            "and (coalesce(:locationId, '') = '' OR l.id = :locationId) " +
            "and (coalesce(:buildingId, '') = '' OR b.id = :buildingId)")
    Page<AsramaVM> filter(
            @Param("locationId") String locationId,
            @Param("buildingId") String buildingId,
            @Param("q") String q,
            Pageable pageable
    );


    @Query("select new id.smartpesantren.web.rest.vm.AsramaVM(a.id, a.code, a.name, a.description, \n" +
            "b.id, b.name, l.name) \n" +
            "from Asrama a \n" +
            "left join a.building b \n" +
            "left join b.location l \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q \n" +
            "and (coalesce(:locationId,'') ='' OR l.id=:locationId) \n" +
            "order by a.name")
    public Iterable<AsramaVM> findAllAsrama(@Param("locationId") String locationId, @Param("q") String q);
}
