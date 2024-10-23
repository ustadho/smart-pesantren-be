package id.smartpesantren.web;

import id.smartpesantren.entity.EducationLevel;
import id.smartpesantren.repository.EducationLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hr/education-level")
public class AcademicLevelResouce {
    @Autowired
    EducationLevelRepository educationLevelRepository;

    @GetMapping("all")
    public Iterable<EducationLevel> findAll() {
        return educationLevelRepository.findAllLevel();
    }
}
