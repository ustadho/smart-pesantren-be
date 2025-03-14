package id.smartpesantren.repository;

import id.smartpesantren.entity.SubjectScheduleTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SubjectScheduleTeacherRepository extends JpaRepository<SubjectScheduleTeacher, String> {
    @Query("from SubjectScheduleTeacher a " +
            "left join fetch a.students " +
            "where a.id=:id")
    public Optional<SubjectScheduleTeacher> findOneById(@Param("id") String id);
}
