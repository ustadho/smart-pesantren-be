package id.smartpesantren.web;

import id.smartpesantren.dto.*;
import id.smartpesantren.repository.SubjectScheduleHistoryRepository;
import id.smartpesantren.repository.SubjectScheduleRepository;
import id.smartpesantren.repository.UserRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.service.SubjectScheduleService;
import id.smartpesantren.web.rest.vm.SubjectScheduleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/academic/subject-schedule")
public class SubjectScheduleResource {
    @Autowired
    SubjectScheduleService subjectScheduleService;

    @Autowired
    SubjectScheduleRepository subjectScheduleRepository;

    @Autowired
    SubjectScheduleHistoryRepository subjectScheduleHistoryRepository;

    @Autowired
    UserRepository userRepository;

    @PutMapping
    public SubjectScheduleVM update(@RequestBody @Valid SubjectScheduleVM vm) {
        return subjectScheduleService.saveOrUpdate(vm);
    }

    @GetMapping("{id}/list-per-day")
    public List<ActivityScheduleByDayDTO> getSchedulesByDay(@PathVariable("id") String classRoomId, @RequestHeader("Timezone") String timeZone) {
        return subjectScheduleService.findAllSchedulePerDay(classRoomId, timeZone);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        subjectScheduleService.deleteById(id);
    }

    @GetMapping("/teachers/{id}")
    public List<PersonSimpleDTO> findAllTeacher(@PathVariable("id") String academicYear) {
        return subjectScheduleRepository.findAllTeacherScheduleToday(academicYear);
    }

    @GetMapping("/by-teacher/{id}")
    public List<MySchedule2DTO> findSubjectScheduleClassRoomByTeacherId(@PathVariable("id") String teacherId) {
        List<MySchedule2DTO> list = subjectScheduleRepository.findTeacherScheduleToday(teacherId);
        return list;
    }

    @GetMapping("/history/{classRoomId}")
    public List<SubjectScheduleHistoryDTO> findAllHistoryByClassRoomId(@PathVariable("classRoomId") String id) {
        return subjectScheduleHistoryRepository.findAllByClassRoomId(id);
    }

    @GetMapping("my-current-schedule")
    List<MySchedule2DTO> findMyCurrentSchedule() {
        return SecurityUtils.getCurrentUserLogin()
                .flatMap(userRepository::findOneByLogin)
                .filter(user -> user.getPerson() != null)
                .map(user -> subjectScheduleRepository.findTeacherScheduleToday("%"+user.getPerson().getId()+"%"))
                .orElseGet(ArrayList::new);
    }

    @GetMapping("my-weekly-schedule")
    List<MyScheduleWeeklyDTO> findMyWeeklySchedule() {
        return SecurityUtils.getCurrentUserLogin()
                .flatMap(userRepository::findOneByLogin)
                .filter(user -> user.getPerson() != null)
                .map(user -> subjectScheduleRepository.findAllMyWeeklySchedule(user.getPerson().getId()))
                .orElseGet(ArrayList::new);
    }

}
