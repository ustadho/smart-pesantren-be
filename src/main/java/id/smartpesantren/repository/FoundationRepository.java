package id.smartpesantren.repository;

import id.smartpesantren.entity.Foundation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FoundationRepository extends JpaRepository<Foundation, String> {
    String FOUNDATION_BY_ID_CACHE = "foundationById";

    @Query("from Foundation c " +
            "where c.id=?1")
    Optional<Foundation> findById(String id);

    @Query("from Foundation c " +
            "where c.id=?#{principal.companyId}")
    Optional<Foundation> findByUserLoggedIn();

}
