package id.smartpesantren.web;

import id.smartpesantren.dto.ActivityScheduleDTO;
import id.smartpesantren.dto.PersonSimpleDTO;
import id.smartpesantren.dto.SubjectScheduleClassRoomDTO;
import id.smartpesantren.repository.SubjectScheduleRepository;
import id.smartpesantren.service.SubjectScheduleService;
import id.smartpesantren.web.rest.vm.SubjectScheduleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/academic/subject-schedule")
public class SubjectScheduleResource {
    @Autowired
    SubjectScheduleService subjectScheduleService;

    @Autowired
    SubjectScheduleRepository subjectScheduleRepository;

    @GetMapping("{id}")
    public List<ActivityScheduleDTO> getSchedules(@PathVariable("id") String classRoomId, @RequestParam("timeZone") String timeZone) {
        return subjectScheduleService.getAllSchedules(classRoomId, timeZone);
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
    public List<SubjectScheduleClassRoomDTO> findSubjectScheduleClassRoomByTeacherId(@PathVariable("id") String id) {
        return subjectScheduleRepository.findSubjectScheduleClassRoomByTeacherId(id);
    }
}
