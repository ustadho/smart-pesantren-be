package id.smartpesantren.web;

import id.smartpesantren.entity.TahfidzTime;
import id.smartpesantren.repository.TahfidzTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

@RestController
@RequestMapping("/api/tahfidz-time")
public class TahfidzTimeResource {
    @Autowired
    TahfidzTimeRepository repository;


    @GetMapping("/all")
    public List<TahfidzTime> getAll() {
        return repository.findAll()
            .stream()
            .sorted(Comparator.comparing(TahfidzTime::getId))
            .collect(Collectors.toList());
    }
}
