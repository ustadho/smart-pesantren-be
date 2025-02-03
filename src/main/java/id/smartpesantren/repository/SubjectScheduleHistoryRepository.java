package id.smartpesantren.repository;

import id.smartpesantren.dto.PersonSimpleDTO;
import id.smartpesantren.dto.SubjectScheduleClassRoomDTO;
import id.smartpesantren.dto.SubjectScheduleHistoryDTO;
import id.smartpesantren.entity.SubjectSchedule;
import id.smartpesantren.entity.SubjectScheduleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectScheduleHistoryRepository extends JpaRepository<SubjectScheduleHistory, String> {
    @Query(value = "select h.id, h.created_by \"createdBy\", h.created_date \"createdDate\", h.log_activity \"activity\", coalesce(md.name, '') \"dayName\", \n" +
            "coalesce(s.name, '') \"subjectName\", coalesce(pd.name, '') \"teacherName\", coalesce(aat.seq) \"seq\", aat.start_time \"startTime\", aat.end_time \"endTime\" \n" +
            "from ac_subject_schedule_history h\n" +
            "left join m_day md on md.id=h.day_id \n" +
            "left join ac_subject s on s.id=h.subject_id \n" +
            "left join person_data pd on pd.id=h.teacher_id \n" +
            "left join ac_activity_time aat on aat.id=h.activity_time_id \n" +
            "where h.class_room_id = :classRoomId\n" +
            "order by h.created_date", nativeQuery = true)
    public List<SubjectScheduleHistoryDTO> findAllByClassRoomId(@Param("classRoomId") String classRoomId);
}
