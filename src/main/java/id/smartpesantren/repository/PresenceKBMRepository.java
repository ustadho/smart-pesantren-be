package id.smartpesantren.repository;

import id.smartpesantren.entity.PresenceKBM;
import id.smartpesantren.entity.PresenceKBMStudent;
import id.smartpesantren.entity.SubjectSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface PresenceKBMRepository extends JpaRepository<PresenceKBM, String> {

}
