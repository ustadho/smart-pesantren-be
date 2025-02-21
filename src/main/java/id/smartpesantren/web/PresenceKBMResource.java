package id.smartpesantren.web;

import id.smartpesantren.dto.PresenceSubjectStudentDTO;
import id.smartpesantren.repository.PresenceKBMStudentRepository;
import id.smartpesantren.service.PresenceKBMService;
import id.smartpesantren.web.rest.vm.PresenceKbmVM;
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

    @PutMapping
    public void createOrUpdate(@RequestBody @Valid PresenceKbmVM vm) {
        presenceKBMService.createOrUpdate(vm);
    }

    @GetMapping("detail-students/{id}")
    public List<PresenceSubjectStudentDTO> findDetailStudentsByClassRoomId(@PathVariable("id") String id) {
        return presenceKBMStudentRepository.findDetailStudentsByClassRoomId(id);
    }
}
