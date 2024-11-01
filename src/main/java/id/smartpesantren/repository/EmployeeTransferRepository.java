package id.smartpesantren.repository;

import id.smartpesantren.dto.EmployeeTransferDTO;
import id.smartpesantren.entity.EmployeeTransfer;
import id.smartpesantren.entity.PersonData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface EmployeeTransferRepository extends JpaRepository<EmployeeTransfer, String> {
    public Optional<EmployeeTransfer> findByEmployeeAndEffectiveDate(PersonData p, Date effectiveDate);
    @Query("select new id.smartpesantren.dto.EmployeeTransferDTO(e) \n" +
            "from EmployeeTransfer e \n" +
            "where e.foundation.id=?#{principal.foundationId} \n" +
            "and upper(e.employee.name) like :q ")
    public Page<EmployeeTransferDTO> filter(@Param("q") String q, Pageable p);
}
