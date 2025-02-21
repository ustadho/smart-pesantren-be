package id.smartpesantren.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.smartpesantren.constant.LogActivityStatus;
import id.smartpesantren.dto.ActivityScheduleByDayDTO;
import id.smartpesantren.dto.ActivityScheduleDTO;
import id.smartpesantren.dto.ActivityTimeDTO;
import id.smartpesantren.dto.EmployeeSimpleDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.SubjectScheduleCustomRepository;
import id.smartpesantren.repository.SubjectScheduleHistoryRepository;
import id.smartpesantren.repository.SubjectScheduleRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.service.dto.PersonDTO;
import id.smartpesantren.web.rest.vm.SubjectScheduleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SubjectScheduleService {
    @Autowired
    SubjectScheduleRepository subjectScheduleRepository;

    @Autowired
    SubjectScheduleHistoryRepository subjectScheduleHistoryRepository;

    @Autowired
    SubjectScheduleHistoryService subjectScheduleHistoryService;

    @Autowired
    private SubjectScheduleCustomRepository customRepository;

    @Autowired
    private ObjectMapper objectMapper;


    public List<ActivityScheduleDTO> getAllSchedules(String classRoomId, String timeZone) {
        String jsonResult = customRepository.findAllSchedules(SecurityUtils.getFoundationId().get(), classRoomId, timeZone);

        try {
            return objectMapper.readValue(jsonResult, new TypeReference<Object>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }

    public List<ActivityScheduleByDayDTO> findAllSchedulePerDay(String classRoomId, String timeZone) {
        String jsonResult = customRepository.findAllSchedulePerDay(classRoomId, timeZone);

        try {
            return objectMapper.readValue(jsonResult, new TypeReference<Object>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }

    @Transactional
    public SubjectScheduleVM saveOrUpdate(SubjectScheduleVM vm) {
        SubjectSchedule ss = fromVm(vm);
        subjectScheduleRepository.save(ss);
        // Save History
        List<SubjectScheduleHistory> histories = new ArrayList<>();
        for(EmployeeSimpleDTO e: vm.getTeachers()) {
            SubjectScheduleHistory sh = subjectScheduleHistoryService.fromOrigin(ss);
            sh.setTeacher(new PersonData(e.getId()));
            if (vm.getId() == null) {
                sh.setLogActivity(LogActivityStatus.INSERT);
            } else {
                sh.setLogActivity(LogActivityStatus.UPDATE);
            }
            histories.add(sh);
        }
        if(histories.size() > 0) {
            subjectScheduleHistoryRepository.saveAll(histories);
        }
        vm.setId(ss.getId());
        return vm;
    }

    public SubjectSchedule fromVm(SubjectScheduleVM vm) {
        SubjectSchedule ss = null;
        if(vm.getId() == null) {
            ss = new SubjectSchedule();
        } else {
            ss = subjectScheduleRepository.findById(vm.getId()).get();
        }
        ss.setDay(new Day(vm.getDayId()));
        ss.setClassRoom(new ClassRoom(vm.getClassRoomId()));
        ss.setSubject(new Subject(vm.getSubjectId()));
        ss.setDuration(vm.getDuration());
//        ss.setActivityTimeStart(new AcademicActivityTime(vm.getActivityTimeId()));
//        ss.setActivityTimeEnd(new AcademicActivityTime(vm.getActivityTimeEndId()));
        ss.setActivityTime(new AcademicActivityTime(vm.getActivityTimeId()));
        Set<PersonData> managedTeachers = ss.getTeachers();
        vm.getTeachers().stream().forEach(s -> {
            managedTeachers.add(new PersonData(s.getId()));
        });
        ss.setTeachers(managedTeachers);

//        ss.setTeacher(new PersonData(vm.getTeacherId()));
        return ss;
    }

    @Transactional
    public void deleteById(String id) {
        Optional<SubjectSchedule> ss = subjectScheduleRepository.findById(id);
        if(ss.isPresent()) {
            SubjectScheduleHistory sh = subjectScheduleHistoryService.fromOrigin(ss.get());
            sh.setLogActivity(LogActivityStatus.DELETE);
            this.subjectScheduleRepository.deleteById(id);
            this.subjectScheduleHistoryRepository.save(sh);
        }
    }

    public SubjectScheduleVM findById(String id) {
        SubjectScheduleVM vm = null;
        SubjectSchedule s  = subjectScheduleRepository.findById(id).get();
        if(s != null) {
            vm.setId(s.getId());
            vm.setSubjectId(s.getSubject().getId());
            vm.setSubjectName(s.getSubject().getName());
            vm.setDayId(s.getDay().getId());
            vm.setDayName(s.getDay().getName());
            vm.setDuration(s.getDuration());
            vm.setActivityTimeId(s.getActivityTime() == null? null: s.getActivityTime().getId());
            vm.setActivityTimeStartId(s.getActivityTimeStart() == null? null: s.getActivityTimeStart().getId());
            vm.setActivityTimeEndId(s.getActivityTimeEnd() == null? null: s.getActivityTimeEnd().getId());
            if(s.getTeachers() != null) {
                for(PersonData t : s.getTeachers()) {
                    EmployeeSimpleDTO d = new EmployeeSimpleDTO();
                    d.setId(t.getId());
                    d.setName(t.getName());
                    d.setEmployeeNo(t.getEmployeeNo());
                    vm.getTeachers().add(d);
                }
            }
//            if(s.getActivityTimes() != null) {
//                for(AcademicActivityTime t : s.getActivityTimes()) {
//                    ActivityTimeDTO d = new ActivityTimeDTO();
//                    d.setId(t.getId());
//                    d.setName(t.getDescription());
//                    d.setSex(t.getSex());
//                    d.setStartTime(t.getStartTime());
//                    d.setEndTime(t.getEndTime());
//                    vm.getActivityTimes().add(d);
//                }
//            }
        }
        return vm;
    }
}
