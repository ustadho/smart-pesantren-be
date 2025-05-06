package id.smartpesantren.repository;

import id.smartpesantren.entity.StudentPresence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPresenceRepository extends JpaRepository<StudentPresence, String> {
}
