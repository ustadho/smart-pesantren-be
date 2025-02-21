package id.smartpesantren.web;

import id.smartpesantren.entity.Day;
import id.smartpesantren.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/day")
public class DayResource {
    @Autowired
    DayRepository dayRepository;

    @GetMapping("all")
    public List<Day> findAll() {
        return dayRepository.findAllDays();
    }
}
