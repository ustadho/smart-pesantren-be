package id.smartpesantren.web;

import id.smartpesantren.entity.PersonTitle;
import id.smartpesantren.repository.EmploymentTypeRepository;
import id.smartpesantren.repository.PersonTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/person-title")
public class PersonTitleResource {
    @Autowired
    PersonTitleRepository repository;

    @GetMapping("all")
    public Iterable<PersonTitle> findAll() {
        List<PersonTitle> list = repository.findAll();
        Collections.sort(list, Comparator.comparingInt(PersonTitle::getSeq));

        return list;
    }
}
