package id.smartpesantren.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.smartpesantren.dto.ActivityScheduleByDayDTO;
import id.smartpesantren.dto.ActivityScheduleDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.SubjectSchedule2Repository;
import id.smartpesantren.repository.SubjectScheduleCustom2Repository;
import id.smartpesantren.repository.SubjectScheduleHistoryRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.SubjectSchedule2VM;
import id.smartpesantren.web.rest.vm.SubjectScheduleVM;
import id.smartpesantren.web.rest.vm.SubjectScheduleVMSubjectTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectSchedule2Service {
    @Autowired
    SubjectSchedule2Repository subjectSchedule2Repository;

    @Autowired
    SubjectScheduleHistoryRepository subjectScheduleHistoryRepository;

    @Autowired
    SubjectScheduleHistoryService subjectScheduleHistoryService;

    @Autowired
    private SubjectScheduleCustom2Repository customRepository;

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
    public SubjectSchedule2VM saveOrUpdate(SubjectSchedule2VM vm) {
        SubjectSchedule2 ss = fromVm(vm);
        subjectSchedule2Repository.save(ss);
        // Save History
        List<SubjectScheduleHistory> histories = new ArrayList<>();
        for(SubjectScheduleTeacher2 e: ss.getSubjectTeachers()) {
//            SubjectScheduleHistory sh = subjectScheduleHistoryService.fromOrigin(e);
//            sh.setTeacher(e.getTeacher());
//            sh.setSubject(e.getSubject());
//            if (vm.getId() == null) {
//                sh.setLogActivity(LogActivityStatus.INSERT);
//            } else {
//                sh.setLogActivity(LogActivityStatus.UPDATE);
//            }
//            histories.add(sh);
        }
        if(histories.isEmpty()) {
            subjectScheduleHistoryRepository.saveAll(histories);
        }
        vm.setId(ss.getId());
        return vm;
    }

    public SubjectSchedule2 fromVm(SubjectSchedule2VM vm) {
        SubjectSchedule2 ss = null;
        if(vm.getId() == null) {
            ss = new SubjectSchedule2();
        } else {
            ss = subjectSchedule2Repository.findById(vm.getId()).get();
        }
        ss.setDay(new Day(vm.getDayId()));
        ss.setClassRoom(new ClassRoom(vm.getClassRoomId()));
        ss.setActivityTimeStart(new AcademicActivityTime(vm.getActivityStartId()));
        ss.setActivityTimeEnd(new AcademicActivityTime(vm.getActivityEndId()));
        ss.setDuration(vm.getDuration());
        for (Iterator<SubjectScheduleTeacher2> iterator = ss.getSubjectTeachers().iterator(); iterator.hasNext();) {
            SubjectScheduleTeacher2 d = iterator.next();
            boolean used = false;
            for(SubjectScheduleVMSubjectTeacher di: vm.getSubjectTeachers()) {
                if(di.getId() != null && di.getId().equalsIgnoreCase(d.getId())) {
                    used = true;
                    break;
                }
            }
            if(!used) {
                iterator.remove();
            }
        }
        for(SubjectScheduleVMSubjectTeacher d: vm.getSubjectTeachers()) {
            SubjectScheduleTeacher2 st = null;
            if(ss.getId() == null) {
                st = new SubjectScheduleTeacher2();
            } else {
                if(d.getId() == null) {
                    st = new SubjectScheduleTeacher2();
                } else {
                    // Check if existing detail needs deletion
                    boolean existingDetailFound = false;
                    for (SubjectScheduleTeacher2 existingDetail : ss.getSubjectTeachers()) {
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
                ss.getSubjectTeachers().add(st);
            }
        }
//        ss.setTeacher(new PersonData(vm.getTeacherId()));
        return ss;
    }

    @Transactional
    public void deleteById(String id) {
        Optional<SubjectSchedule2> ss = subjectSchedule2Repository.findById(id);
        if(ss.isPresent()) {
            for(SubjectScheduleTeacher2 st: ss.get().getSubjectTeachers()) {
//                SubjectScheduleHistory sh = subjectScheduleHistoryService.fromOrigin(st);
//                sh.setLogActivity(LogActivityStatus.DELETE);
//                this.subjectSchedule2Repository.deleteById(id);
//                this.subjectScheduleHistoryRepository.save(sh);
            }
        }
    }

    public SubjectScheduleVM findById(String id) {
        SubjectScheduleVM vm = null;
        SubjectSchedule2 s  = subjectSchedule2Repository.findById(id).get();
        if(s != null) {
            vm.setId(s.getId());
            vm.setDayId(s.getDay().getId());
            vm.setDayName(s.getDay().getName());
            vm.setDuration(s.getDuration());
            vm.setActivityTimeStartId(s.getActivityTimeStart() == null? null: s.getActivityTimeStart().getId());
            vm.setActivityTimeEndId(s.getActivityTimeEnd() == null? null: s.getActivityTimeEnd().getId());
            if(s.getSubjectTeachers() != null) {
                for(SubjectScheduleTeacher2 t : s.getSubjectTeachers()) {
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
