package id.smartpesantren.repository;

import id.smartpesantren.entity.PresensiAsrama;
import id.smartpesantren.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

public interface PresensiAsramaRepository extends JpaRepository<PresensiAsrama, String> {
    Optional<PresensiAsrama> findBySantriAndTanggalAndPresenceType(Student santri, @NotNull Date tanggal, String presenceType);
}
