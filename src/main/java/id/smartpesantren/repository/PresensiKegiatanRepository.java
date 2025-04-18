package id.smartpesantren.repository;

import id.smartpesantren.entity.PresensiKegiatan;
import id.smartpesantren.web.rest.vm.PresensiKegiatanVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PresensiKegiatanRepository extends JpaRepository<PresensiKegiatan, String> {
    @Query("select new id.smartpesantren.web.rest.vm.PresensiKegiatanVM(a) " +
            "from PresensiKegiatan a " +
            "")
    Page<PresensiKegiatanVM> filter(Pageable p);
}
