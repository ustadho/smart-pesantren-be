package id.smartpesantren.repository;

import id.smartpesantren.dto.TahfidzKonversiRekapJuzQuery;
import id.smartpesantren.entity.TahfidzKonversi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TahfidzKonversiRepository extends JpaRepository<TahfidzKonversi, Integer> {
    Optional<TahfidzKonversi> findByJmlHalaman(Integer jumlahHalaman);

    @Query("from TahfidzKonversi t order by t.jmlHalaman" )
    List<TahfidzKonversi> findAllKonversi();

    @Query("from TahfidzKonversi t where t.jmlHalaman > :jml order by t.jmlHalaman" )
    List<TahfidzKonversi> findAllKonversiGreaterThanJml(@Param("jml") Integer jml);

    @Query(
            value =
                    "WITH a AS ( " +
                    "   SELECT juz, COUNT(id) AS halaman_count, MIN(id) AS min_halaman, MAX(id) AS max_halaman " +
                    "   FROM tahfidz_konversi " +
                    "   WHERE juz = ANY(string_to_array(:juzs, ',')::::int[]) " +
                    "   GROUP BY juz " +
                    ") " +
                    "SELECT " +
                    "   COUNT(juz)::::integer AS \"totalJuz\", " +
                    "   SUM(halaman_count)::::integer AS \"jumlahHalaman\", " +
                    "   MIN(min_halaman) AS \"awalHalaman\", " +
                    "   MAX(max_halaman) AS \"akhirHalaman\" " +
                    "FROM a",
            nativeQuery = true
    )
    TahfidzKonversiRekapJuzQuery rekapJuz(@Param("juzs") String juzs);

    @Query(value = "select t.id from tahfidz_konversi t " +
            "WHERE juz = ANY(string_to_array(:juzs, ',')::::int[]) order by t.id", nativeQuery = true)
    List<Integer> findAllPageInJuz(@Param("juzs") String juzs);
}
