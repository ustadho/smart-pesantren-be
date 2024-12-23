package id.smartpesantren.repository;

import id.smartpesantren.dto.LocationDTO;
import id.smartpesantren.entity.Building;
import id.smartpesantren.web.rest.vm.BuildingVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BuildingRepository extends JpaRepository<Building, String> {
    @Query("select new id.smartpesantren.web.rest.vm.BuildingVM(a.id, a.code, a.name, a.description, a.color) " +
            "from Building a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q")
    public Page<BuildingVM> filter(@Param("q") String q, Pageable p);

    @Query("select new id.smartpesantren.web.rest.vm.BuildingVM(a.id, a.code, a.name, a.description, a.color) " +
            "from Building a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and upper(a.name) like :q \n" +
            "order by a.name")
    public Iterable<BuildingVM> findAllBuilding(@Param("q") String q);
}
