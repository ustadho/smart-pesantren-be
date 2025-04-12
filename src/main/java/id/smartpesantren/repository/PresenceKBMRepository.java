package id.smartpesantren.repository;

import id.smartpesantren.entity.PresenceKBM;
import id.smartpesantren.entity.PresenceKBMStudent;
import id.smartpesantren.entity.SubjectSchedule;
import id.smartpesantren.entity.SubjectScheduleTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface PresenceKBMRepository extends JpaRepository<PresenceKBM, String> {
    Optional<PresenceKBM> findBySubjectScheduleTeacherAndPresenceDate(SubjectScheduleTeacher st, LocalDate presenceDate);
}
