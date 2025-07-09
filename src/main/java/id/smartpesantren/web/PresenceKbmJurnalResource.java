package id.smartpesantren.web;

import id.smartpesantren.dto.JurnalVm;
import id.smartpesantren.dto.PresenceKbmJurnalHistoryDTO;
import id.smartpesantren.entity.PresenceKBM;
import id.smartpesantren.entity.PresenceKBMJurnal;
import id.smartpesantren.repository.PresenceKBMJournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/academic/presence-kbm-jurnal")
public class PresenceKbmJurnalResource {
    @Autowired
    PresenceKBMJournalRepository presenceKBMJournalRepository;

    @GetMapping("by-presence-id/{id}")
    public JurnalVm findByPresenceId(@PathVariable String id) {
        Optional<PresenceKBMJurnal> rpp = presenceKBMJournalRepository.findByPresenceId(id);
        if (rpp.isPresent()) {
            JurnalVm vm = new JurnalVm();
            vm.setId(rpp.get().getId());
            vm.setPresenceId(rpp.get().getPresenceKBM().getId());
            vm.setMateriPokok(rpp.get().getMateriPokok());
            vm.setKegiatan(rpp.get().getKegiatan());
            vm.setPenilaian(rpp.get().getPenilaian());
            return vm;
        }
        return null;
    }

    @PostMapping
    public JurnalVm createRpp(@RequestBody @Valid JurnalVm vm) {
        Optional<PresenceKBMJurnal> rpp = presenceKBMJournalRepository.findByPresenceId(vm.getPresenceId());
        if (rpp.isPresent()) {
            rpp.get().setKegiatan(vm.getKegiatan());
            rpp.get().setMateriPokok(vm.getMateriPokok());
            rpp.get().setPenilaian(vm.getPenilaian());
            presenceKBMJournalRepository.save(rpp.get());
            return vm;
        }
        PresenceKBMJurnal r = new PresenceKBMJurnal();
        r.setPresenceKBM(new PresenceKBM(vm.getPresenceId()));
        r.setKegiatan(vm.getKegiatan());
        r.setMateriPokok(vm.getMateriPokok());
        r.setPenilaian(vm.getPenilaian());
        presenceKBMJournalRepository.save(r);
        vm.setId(r.getId());
        return vm;
    }

    @GetMapping("history/{id}")
    public List<PresenceKbmJurnalHistoryDTO> findById(@PathVariable String id) {
        return presenceKBMJournalRepository.findHistoryByScheduleTeacherId(id);
    }
}
