package id.smartpesantren.repository;

import id.smartpesantren.dto.StudentDTO;
import id.smartpesantren.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("select new id.smartpesantren.dto.StudentDTO(a) \n " +
            "from Student a " +
            "where a.foundation.id=?#{principal.foundationId}")
    public Page<StudentDTO> filter(@Param("q") String q, Pageable p);
}
