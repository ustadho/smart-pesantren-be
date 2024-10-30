package id.smartpesantren.repository;

import id.smartpesantren.dto.BaseEnityDTO;
import id.smartpesantren.entity.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatus, String> {
    @Query("select new id.smartpesantren.dto.BaseEnityDTO(a.id, a.name) \n" +
            "from EmployeeStatus a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "order by a.seq")
    public Iterable<BaseEnityDTO> retrieveAll();
}