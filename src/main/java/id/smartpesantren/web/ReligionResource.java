package id.smartpesantren.web;

import id.smartpesantren.entity.Religion;
import id.smartpesantren.repository.ReligionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/religion")
public class ReligionResource {
    @Autowired
    ReligionRepository repository;

    @GetMapping("all")
    public Iterable<Religion> findAll() {
        return repository.findAll();
    }
}
