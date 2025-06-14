package id.smartpesantren.web;

import id.smartpesantren.dto.PresenceSubjectStudentDTO;
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
}
