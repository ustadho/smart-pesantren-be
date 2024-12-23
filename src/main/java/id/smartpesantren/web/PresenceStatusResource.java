package id.smartpesantren.web;

import id.smartpesantren.entity.PresenceStatus;
import id.smartpesantren.repository.PresenceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/presence-status")
public class PresenceStatusResource {
    @Autowired
    PresenceStatusRepository repository;

    @GetMapping("/all")
    public List<PresenceStatus> findAll() {
        return repository.findAll();
    }
}
