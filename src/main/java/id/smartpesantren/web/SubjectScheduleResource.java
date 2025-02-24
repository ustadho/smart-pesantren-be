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
    SubjectScheduleHistoryRepository subjectScheduleHistoryRepository;

    @Autowired
    SubjectScheduleRepository subjectScheduleRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("{id}/list")
    public List<ActivityScheduleDTO> getSchedules(@PathVariable("id") String classRoomId, @RequestHeader("Timezone") String timeZone) {
        return subjectScheduleService.getAllSchedules(classRoomId, timeZone);
    }

    @GetMapping("{id}/list-per-day")
    public List<ActivityScheduleByDayDTO> getSchedulesByDay(@PathVariable("id") String classRoomId, @RequestHeader("Timezone") String timeZone) {
        return subjectScheduleService.findAllSchedulePerDay(classRoomId, timeZone);
    }

    @GetMapping("/{id}")
    public SubjectScheduleVM findById(@PathVariable("id") String id) {
        return subjectScheduleService.findById(id);
    }

    @PutMapping
    public SubjectScheduleVM update(@RequestBody @Valid SubjectScheduleVM vm) {
        return subjectScheduleService.saveOrUpdate(vm);
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
    public List<MyScheduleDTO> findSubjectScheduleClassRoomByTeacherId(@PathVariable("id") String id) {
        List<MyScheduleDTO> list = subjectScheduleRepository.findTeacherScheduleToday("%"+id+"%");
        return list;
    }

    @GetMapping("/history/{classRoomId}")
    public List<SubjectScheduleHistoryDTO> findAllHistoryByClassRoomId(@PathVariable("classRoomId") String id) {
        return subjectScheduleHistoryRepository.findAllByClassRoomId(id);
    }

    @GetMapping("my-current-schedule")
    List<MyScheduleDTO> findMyCurrentSchedule() {
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
