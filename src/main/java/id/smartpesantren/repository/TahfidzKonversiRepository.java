package id.smartpesantren.repository;

import id.smartpesantren.entity.TahfidzKonversi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TahfidzKonversiRepository extends JpaRepository<TahfidzKonversi, Integer> {
    Optional<TahfidzKonversi> findByNoHalaman(String code);
}
