package id.smartpesantren.repository;

import id.smartpesantren.dto.ClassRoomDTO;
import id.smartpesantren.entity.AcademicYear;
import id.smartpesantren.entity.ClassRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, String> {
    @Query("select new id.smartpesantren.dto.ClassRoomDTO(" +
            "a.id, b.id, b.code, c.id, c.level, a.code, a.name, a.capacity, a.description) " +
            "from ClassRoom a " +
            "join a.academicYear b " +
            "join a.level c " +
            "where b.isDefault=true " +
            "order by c.level desc")
    public Iterable<ClassRoomDTO> findAllDefault();

    @Query("select new id.smartpesantren.dto.ClassRoomDTO(" +
            "a.id, b.id, b.code, c.id, c.level, a.code, a.name, a.capacity, a.description) " +
            "from ClassRoom a " +
            "join a.academicYear b " +
            "join a.level c " +
            "where (coalesce(:year,'') ='' OR b.id=:year) " +
            "and (upper(a.name) like :q OR upper(a.description) like :q) ")
    public Page<ClassRoomDTO> filter(@Param("year") String year, @Param("q") String q, Pageable p);

    Optional<ClassRoom> findByAcademicYearAndCode(AcademicYear ay, String code);

    @Query("select new id.smartpesantren.dto.ClassRoomDTO(" +
            "a.id, b.id, b.code, c.id, c.level, a.code, a.name, a.capacity, a.description) " +
            "from ClassRoom a " +
            "join a.academicYear b " +
            "join a.level c " +
            "where (coalesce(:year,'') ='' OR b.id=:year) " +
            "order by c.level ")
    Iterable<ClassRoomDTO> findAll(@Param("year") String year);
}
