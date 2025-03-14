package id.smartpesantren.web;

import id.smartpesantren.dto.*;
import id.smartpesantren.repository.SubjectScheduleHistoryRepository;
import id.smartpesantren.repository.SubjectScheduleRepository;
import id.smartpesantren.repository.UserRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.service.SubjectScheduleService;
import id.smartpesantren.web.rest.errors.DataNotFoundException;
import id.smartpesantren.web.rest.vm.SubjectScheduleVM;
import id.smartpesantren.web.rest.vm.SubjectScheduleVMSubjectTeacher;
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

    @GetMapping("/teachers")
    public List<PersonSimpleDTO> findAllTeacher(
            @RequestParam("academicYear") String academicYear,
            @RequestParam("dayId") Integer dayId) {
        return subjectScheduleRepository.findAllTeacherScheduleToday(academicYear, dayId);
    }

    @GetMapping("/by-teacher/{id}")

    public List<MyScheduleDTO> findSubjectScheduleClassRoomByTeacherId(@PathVariable("id") String teacherId) {
        List<MyScheduleDTO> list = subjectScheduleRepository.findTeacherScheduleToday(teacherId);
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

    @GetMapping("lookup-unmapped-student/{scheduleId}")
    public List<IStudentQuery> findAllUnmappedStudent(@PathVariable("scheduleId") String scheduleId) {
        return subjectScheduleRepository.findAllUnMappedStudentInClassRoom(scheduleId);
    }

    @GetMapping("lookup-mapped-student/{scheduleId}")
    public List<IStudentQuery> findAllMappedStudent(@PathVariable("scheduleId") String scheduleId) {
        return subjectScheduleRepository.findAllMappedStudentInClassRoom(scheduleId);
    }

    @PutMapping("subject-teacher")
    public SubjectScheduleVMSubjectTeacher updateSubjectTeacher(@RequestBody @Valid SubjectScheduleVMSubjectTeacher vm) {
        if(vm.getId() == null) {
            throw new DataNotFoundException("id data yang akan diupdate tidak ada");
        }
        return subjectScheduleService.updateSubjectTeacher(vm);
    }

}
