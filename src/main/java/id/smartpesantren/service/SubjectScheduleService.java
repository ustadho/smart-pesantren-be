package id.smartpesantren.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.smartpesantren.constant.LogActivityStatus;
import id.smartpesantren.dto.*;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.SubjectScheduleCustomRepository;
import id.smartpesantren.repository.SubjectScheduleHistoryRepository;
import id.smartpesantren.repository.SubjectScheduleRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.SubjectScheduleVM;
import id.smartpesantren.web.rest.vm.SubjectScheduleVMSubjectTeacher;
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
        for(SubjectScheduleTeacher e: ss.getTeachers()) {
            SubjectScheduleHistory sh = subjectScheduleHistoryService.fromOrigin(e);
            sh.setTeacher(e.getTeacher());
            sh.setSubject(e.getSubject());
            if (vm.getId() == null) {
                sh.setLogActivity(LogActivityStatus.INSERT);
            } else {
                sh.setLogActivity(LogActivityStatus.UPDATE);
            }
            histories.add(sh);
        }
        if(histories.isEmpty()) {
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
        ss.setActivityTime(new AcademicActivityTime(vm.getActivityTimeId()));
        ss.setDay(new Day(vm.getDayId()));
        ss.setClassRoom(new ClassRoom(vm.getClassRoomId()));
        ss.setDuration(vm.getDuration());
        for (Iterator<SubjectScheduleTeacher> iterator = ss.getTeachers().iterator(); iterator.hasNext();) {
            SubjectScheduleTeacher d = iterator.next();
            boolean used = false;
            for(SubjectScheduleVMSubjectTeacher di: vm.getSubjects()) {
                if(di.getId() != null && di.getId().equalsIgnoreCase(d.getId())) {
                    used = true;
                    break;
                }
            }
            if(!used) {
                iterator.remove();
            }
        }
        for(SubjectScheduleVMSubjectTeacher d: vm.getSubjects()) {
            SubjectScheduleTeacher st = null;
            if(ss.getId() == null) {
                st = new SubjectScheduleTeacher();
            } else {
                if(d.getId() == null) {
                    st = new SubjectScheduleTeacher();
                } else {
                    // Check if existing detail needs deletion
                    boolean existingDetailFound = false;
                    for (SubjectScheduleTeacher existingDetail : ss.getTeachers()) {
                        if (existingDetail.getId().equals(d.getId())) {
                            st = existingDetail;
                            existingDetailFound = true;
                            break;
                        }
                    }
                    if(!existingDetailFound) {
                        System.out.println("detail not exists");
                    }
                }
            }
            st.setSchedule(ss);
            st.setSubject(new Subject(d.getSubjectId()));
            st.setId(d.getId());
            st.setTeacher(new PersonData(d.getTeacherId()));
            if(st.getId() == null) {
                ss.getTeachers().add(st);
            }
        }
//        ss.setTeacher(new PersonData(vm.getTeacherId()));
        return ss;
    }

    @Transactional
    public void deleteById(String id) {
        Optional<SubjectSchedule> ss = subjectScheduleRepository.findById(id);
        if(ss.isPresent()) {
            for(SubjectScheduleTeacher st: ss.get().getTeachers()) {
                SubjectScheduleHistory sh = subjectScheduleHistoryService.fromOrigin(st);
                sh.setLogActivity(LogActivityStatus.DELETE);
                this.subjectScheduleRepository.deleteById(id);
                this.subjectScheduleHistoryRepository.save(sh);
            }
        }
    }

    public SubjectScheduleVM findById(String id) {
        SubjectScheduleVM vm = null;
        SubjectSchedule s  = subjectScheduleRepository.findById(id).get();
        if(s != null) {
            vm.setId(s.getId());
            vm.setDayId(s.getDay().getId());
            vm.setDayName(s.getDay().getName());
            vm.setDuration(s.getDuration());
            vm.setActivityTimeId(s.getActivityTime() == null? null: s.getActivityTime().getId());
            vm.setActivityTimeStartId(s.getActivityTimeStart() == null? null: s.getActivityTimeStart().getId());
            vm.setActivityTimeEndId(s.getActivityTimeEnd() == null? null: s.getActivityTimeEnd().getId());
            if(s.getTeachers() != null) {
                for(SubjectScheduleTeacher t : s.getTeachers()) {
                    SubjectScheduleVMSubjectTeacher d = new SubjectScheduleVMSubjectTeacher();
                    d.setId(t.getId());
                    d.setSubjectId(t.getSubject().getId());
                    d.setSubjectName(t.getSubject().getName());
                    d.setTeacherId(t.getTeacher().getId());
                    d.setTeacherName(t.getTeacher().getName());
                    d.setEmployeeNo(t.getTeacher().getEmployeeNo());
                    vm.getSubjects().add(d);
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
