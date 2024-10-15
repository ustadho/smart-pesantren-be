package id.smartpesantren.repository;

import id.smartpesantren.entity.Foundation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoundationRepository extends JpaRepository<Foundation, Integer> {
    String FOUNDATION_BY_ID_CACHE = "foundationById";
    Optional<Foundation> findTop1ByOrderById();
}
