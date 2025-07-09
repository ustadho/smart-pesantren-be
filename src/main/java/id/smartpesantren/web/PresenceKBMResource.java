package id.smartpesantren.web;

import id.smartpesantren.dto.PresenceKBMTeacherQuery;
import id.smartpesantren.dto.PresenceKbmStudentDetailVM;
import id.smartpesantren.dto.PresenceKbmStudentVM;
import id.smartpesantren.dto.PresenceSubjectStudentDTO;
import id.smartpesantren.repository.PresenceKBMRepository;
import id.smartpesantren.repository.PresenceKBMStudentRepository;
import id.smartpesantren.service.PresenceKBMService;
import id.smartpesantren.web.rest.vm.PresenceKbmVM;
import id.smartpesantren.web.rest.vm.PresenceKbmVMStudent;
import id.smartpesantren.web.rest.vm.PresenceKbmVMTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/academic/presence-kbm")
public class PresenceKBMResource {
    @Autowired
    PresenceKBMService presenceKBMService;

    @Autowired
    PresenceKBMStudentRepository presenceKBMStudentRepository;

    @Autowired
    PresenceKBMRepository presenceKBMRepository;

    @PutMapping("/teacher")
    public PresenceKbmVMTeacher createOrUpdatePresenceKBMTeacher(@RequestBody @Valid PresenceKbmVMTeacher vm) {
        return presenceKBMService.createOrUpdatePresenceKBMTeacher(vm);
    }

    @PutMapping
    public void createOrUpdate(@RequestBody @Valid PresenceKbmVM vm) {
        presenceKBMService.createOrUpdate(vm);
    }

    @GetMapping("detail-students/{id}")
    public List<PresenceSubjectStudentDTO> findDetailStudentsByClassRoomId(@PathVariable("id") String id) {
        return presenceKBMStudentRepository.findDetailStudentsBySubjectTeacherId(id);
    }

    @GetMapping("teacher/{id}/{presenceDate}")
    public PresenceKBMTeacherQuery findPresenceKBMTeacher(@PathVariable("id") String id, @PathVariable("presenceDate") String presenceDate) {
        return presenceKBMRepository.findPresenceKBMTeacherQuery(id, presenceDate);
    }

    @GetMapping("students/{id}/{presenceDate}")
    public PresenceKbmStudentVM findPresenceKBMStudent(@PathVariable("id") String id, @PathVariable("presenceDate") String presenceDate) {
        PresenceKbmStudentVM vm = null;
        PresenceKBMTeacherQuery t = presenceKBMRepository.findPresenceKBMTeacherQuery(id, presenceDate);
        if(t != null) {
            vm = new PresenceKbmStudentVM();
            vm.setId(t.getId());
            vm.setStudentCount(t.getStudentCount());

            List<PresenceSubjectStudentDTO> det = presenceKBMStudentRepository.findByDetailStudentByScheduleIdAndDate(id, presenceDate);
            if(det != null) {
                vm.setAlphaCount(det.get(0).getAplhaCount());
                vm.setIzinCount(det.get(0).getIzinCount());
                vm.setSakitCount(det.get(0).getSakitCount());
                for(PresenceSubjectStudentDTO d: det) {
                    PresenceKbmStudentDetailVM dvm = new PresenceKbmStudentDetailVM();
                    dvm.setId(d.getPresenceId());
                    dvm.setStudentId(d.getStudentId());
                    dvm.setStudentName(d.getStudentName());
                    dvm.setClassRoom(t.getClassRoomName());
                    dvm.setStatusId(d.getPresenceStatusId() == null? null: d.getPresenceStatusId());
                    dvm.setStatusName(d.getPresenceStatusName());
                    dvm.setPhoto(d.getPhoto());
                    dvm.setNote(d.getNote());
                    vm.getDetails().add(dvm);
                }
            }
            return vm;
        }
        return vm;
    }

    @PutMapping("/students")
    public PresenceKbmStudentVM createOrUpdatePresenceKBMStudents(@RequestBody @Valid PresenceKbmStudentVM vm) {
        return presenceKBMService.createOrUpdatePresenceKBMStudents(vm);
    }
}
