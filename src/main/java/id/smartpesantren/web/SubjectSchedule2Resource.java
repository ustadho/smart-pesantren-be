package id.smartpesantren.web;

import id.smartpesantren.dto.*;
import id.smartpesantren.repository.SubjectSchedule2Repository;
import id.smartpesantren.repository.SubjectScheduleHistoryRepository;
import id.smartpesantren.repository.SubjectScheduleRepository;
import id.smartpesantren.repository.UserRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.service.SubjectSchedule2Service;
import id.smartpesantren.service.SubjectScheduleService;
import id.smartpesantren.web.rest.vm.SubjectSchedule2VM;
import id.smartpesantren.web.rest.vm.SubjectScheduleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/academic/subject-schedule2")
public class SubjectSchedule2Resource {
    @Autowired
    SubjectSchedule2Service subjectScheduleService;


    @PutMapping
    public SubjectSchedule2VM update(@RequestBody @Valid SubjectSchedule2VM vm) {
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
}
