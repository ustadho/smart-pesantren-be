package id.smartpesantren.repository;

import id.smartpesantren.dto.MyScheduleDTO;
import id.smartpesantren.dto.MyScheduleWeeklyDTO;
import id.smartpesantren.dto.PersonSimpleDTO;
import id.smartpesantren.dto.SubjectScheduleClassRoomDTO;
import id.smartpesantren.entity.SubjectSchedule;
import id.smartpesantren.entity.SubjectSchedule2;
import id.smartpesantren.entity.SubjectScheduleTeacher2;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubjectSchedule2Repository extends JpaRepository<SubjectSchedule2, String> {

}
