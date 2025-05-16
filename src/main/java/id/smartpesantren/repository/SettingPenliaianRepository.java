package id.smartpesantren.repository;

import id.smartpesantren.dto.SettingPenilaianVM;
import id.smartpesantren.entity.SettingPenilaian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SettingPenliaianRepository extends JpaRepository<SettingPenilaian, String> {
    @Query("select new id.smartpesantren.dto.SettingPenilaianVM(s) " +
            "from SettingPenilaian s " +
            "where s.foundation.id = ?#{principal.foundationId} " +
            "and s.institution.id = :institutionId")
    public SettingPenilaianVM findByInstitutionId(@Param("institutionId") String institutionId);

}
