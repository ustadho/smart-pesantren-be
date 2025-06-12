package id.smartpesantren.web;

import id.smartpesantren.dto.PresenceSubjectStudentDTO;
import id.smartpesantren.repository.PresensiAsramaRepository;
import id.smartpesantren.service.PresensiAsramaService;
import id.smartpesantren.web.rest.vm.PresensiAsramaVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pengasuhan/presensi-asrama")
public class PresensiAsramaResource {
    @Autowired
    PresensiAsramaService service;

    @Autowired
    PresensiAsramaRepository presensiAsramaRepository;

    @PostMapping
    public PresensiAsramaVM createPresensiAsrama(@RequestBody PresensiAsramaVM vm) {
        return service.createOrUpdate(vm);
    }

    @GetMapping("detail-students/{id}")
    public List<PresenceSubjectStudentDTO> findDetailStudentsByClassRoomId(@PathVariable("id") String id) {
        return presensiAsramaRepository.findSantriByAsrama(id);
    }

    @PutMapping(value = "multiple/{asramaId}", consumes = "application/json")
    public List<PresenceSubjectStudentDTO> createMultiplePresensiAsrama(
        @PathVariable("asramaId") String asramaId,
        @RequestBody List<PresenceSubjectStudentDTO> vms) {
        return service.createOrUpdateMultiple(asramaId, vms);
    }
}
