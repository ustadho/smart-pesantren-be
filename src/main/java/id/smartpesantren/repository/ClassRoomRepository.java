package id.smartpesantren.repository;

import id.smartpesantren.dto.ClassRoomDTO;
import id.smartpesantren.entity.AcademicYear;
import id.smartpesantren.entity.ClassRoom;
import id.smartpesantren.entity.Foundation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, String> {
    @Query("select new id.smartpesantren.dto.ClassRoomDTO(a) \n" +
            "from ClassRoom a \n" +
            "join a.academicYear b \n" +
            "join a.classLevel c \n" +
            "where b.isDefault=true \n" +
            "and a.foundation.id=?#{principal.foundationId} \n" +
            "order by c.level desc")
    public Iterable<ClassRoomDTO> findAllDefault();

    @Query("select new id.smartpesantren.dto.ClassRoomDTO(a.id, b.id, b.code, a.institution.name, c.educationLevel.name, c.level,\n" +
            "a.code, a.name, a.sex, case when a.sex='M' then 'Putra' else 'Putri' end,  \n" +
            "a.capacity, a.description, a.room, a.location.name) \n" +
            "from ClassRoom a \n" +
            "join a.academicYear b \n" +
            "join a.classLevel c \n" +
            "where b.isDefault=true \n" +
            "and a.institution.id=:institutionId \n" +
            "and a.academicYear.id=:academicYearId \n" +
            "order by c.level asc, a.name")
    public List<ClassRoomDTO> findAllByAcademicYear(@Param("institutionId") String institutionId, @Param("academicYearId") String academicYearId);

    @Query("select new id.smartpesantren.dto.ClassRoomDTO(a) \n" +
            "from ClassRoom a \n" +
            "join a.academicYear b \n" +
            "join a.classLevel c \n" +
            "where (coalesce(:year,'') ='' OR b.id=:year) \n" +
            "and a.foundation.id=?#{principal.foundationId} \n" +
            "and (coalesce(:institutionId,'')='' OR a.institution.id=:institutionId) \n" +
            "and (upper(a.name) like :q OR upper(a.description) like :q) ")
    public Page<ClassRoomDTO> filter(@Param("year") String year, @Param("institutionId") String institutionId, @Param("q") String q, Pageable p);

    Optional<ClassRoom> findByFoundationAndAcademicYearAndCode(Foundation f, AcademicYear ay, String code);

    @Query("select new id.smartpesantren.dto.ClassRoomDTO(a) \n" +
            "from ClassRoom a \n" +
            "join a.academicYear b \n" +
            "join a.classLevel c \n" +
            "where (coalesce(:year,'') ='' OR b.id=:year) \n" +
            "and a.foundation.id=?#{principal.foundationId} \n" +
            "and (coalesce(:institutionId,'')='' OR a.institution.id=:institutionId ) \n" +
            "order by c.level ")
    Iterable<ClassRoomDTO> findAll(@Param("year") String year, @Param("institutionId") String institutionId);
}
