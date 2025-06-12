package id.smartpesantren.repository;

import id.smartpesantren.entity.TahfidzKonversi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TahfidzKonversiRepository extends JpaRepository<TahfidzKonversi, Integer> {
    Optional<TahfidzKonversi> findByJmlHalaman(Integer jumlahHalaman);

    @Query("from TahfidzKonversi t order by t.jmlHalaman" )
    List<TahfidzKonversi> findAllKonversi();
}
