package id.smartpesantren.web;

import id.smartpesantren.service.PresenceKBMService;
import id.smartpesantren.web.rest.vm.PresenceKbmVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/academic/presence-kbm")
public class PresenceKBMResource {
    @Autowired
    PresenceKBMService presenceKBMService;

    @PutMapping
    public PresenceKbmVM createOrUpdate(@RequestBody @Valid PresenceKbmVM vm) {
        return presenceKBMService.createOrUpdate(vm);
    }

    @GetMapping
    public PresenceKbmVM findByPresenceDateAndSchedule(@RequestParam("date") String date, @RequestParam("schid") String scheduleId) {
        PresenceKbmVM res = presenceKBMService.findByPresenceDateAndSchedule(date, scheduleId) ;
        return res;
    }
}
