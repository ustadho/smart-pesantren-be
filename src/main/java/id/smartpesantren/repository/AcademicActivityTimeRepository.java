package id.smartpesantren.repository;

import id.smartpesantren.dto.AcademicActivityTimeDTO;
import id.smartpesantren.entity.AcademicActivityTime;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.Institution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AcademicActivityTimeRepository extends JpaRepository<AcademicActivityTime, String> {
    @Query("select new id.smartpesantren.dto.AcademicActivityTimeDTO(a) \n" +
            "from AcademicActivityTime a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and (coalesce(:institutionId, '')='' OR a.institution.id=:institutionId) \n" +
            "and (coalesce(:sex, '')='' OR a.sex=:sex) \n"
            )
    Page<AcademicActivityTimeDTO> filter(@Param("institutionId") String institutionId, @Param("sex") String sex, Pageable p);

    @Query("select new id.smartpesantren.dto.AcademicActivityTimeDTO(a) \n" +
            "from AcademicActivityTime a \n" +
            "where a.foundation.id=?#{principal.foundationId} \n" +
            "and (coalesce(:institutionId, '')='' OR a.institution.id=:institutionId) \n" +
            "and (coalesce(:sex, '')='' OR a.sex=:sex) \n" +
            "order by a.seq, a.startTime")
    List<AcademicActivityTimeDTO> findAllActivityTime(@Param("institutionId") String institutionId,
                                                      @Param("sex") String sex);

    public Optional<AcademicActivityTime> findByFoundationAndInstitutionAndSexAndSeq(Foundation foundation, Institution institution, String sex, Integer seq);

    @Query("SELECT COUNT(a) FROM AcademicActivityTime a " +
            "WHERE a.foundation = :foundation \n" +
            "AND a.institution = :institution \n" +
            "AND a.sex=:sex \n" +
            "AND ((:startTime < a.endTime AND :endTime > a.startTime))")
    long countOverlappingTimes(
            @Param("foundation") Foundation foundation,
            @Param("institution") Institution institution,
            @Param("sex") String sex,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime
    );
}
