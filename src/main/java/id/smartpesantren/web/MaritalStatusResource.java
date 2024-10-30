package id.smartpesantren.web;

import id.smartpesantren.entity.MaritalStatus;
import id.smartpesantren.repository.MaritalStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/marital-status")
public class MaritalStatusResource {
    @Autowired
    MaritalStatusRepository maritalStatusRepository;

    @GetMapping("all")
    public List<MaritalStatus> findAll() {
        return maritalStatusRepository.findAll();
    }
}
