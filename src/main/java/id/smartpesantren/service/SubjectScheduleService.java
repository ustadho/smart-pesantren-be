package id.smartpesantren.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.smartpesantren.dto.ActivityScheduleDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.SubjectScheduleCustomRepository;
import id.smartpesantren.repository.SubjectScheduleRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.SubjectScheduleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectScheduleService {
    @Autowired
    SubjectScheduleRepository subjectScheduleRepository;

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

    public SubjectScheduleVM saveOrUpdate(SubjectScheduleVM vm) {
        SubjectSchedule ss = fromVm(vm);
        subjectScheduleRepository.save(ss);
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
        ss.setActivityTime(new AcademicActivityTime(vm.getActivityTimeId()));
        ss.setTeacher(new PersonData(vm.getTeacherId()));
        return ss;
    }

    public void deleteById(String id) {
        this.subjectScheduleRepository.deleteById(id);
    }

}
