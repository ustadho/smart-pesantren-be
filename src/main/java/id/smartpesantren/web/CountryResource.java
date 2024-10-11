package id.smartpesantren.web;

import id.smartpesantren.entity.Country;
import id.smartpesantren.repository.CountryRepository;
import id.smartpesantren.web.rest.errors.CodeAlreadyUsedException;
import id.smartpesantren.web.rest.errors.DataNotFoundException;
import id.smartpesantren.web.rest.errors.EmailAlreadyUsedException;
import javassist.NotFoundException;
import org.apache.poi.ss.formula.functions.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/country")
public class CountryResource {
    @Autowired
    CountryRepository countryRepository;

    @GetMapping
    public Page<Country> filterCountry(@RequestParam(value = "q", required = false) String q, Pageable p) {
        q = q == null? "": q.toUpperCase();
        q = "%"+q+"%";
        return countryRepository.filterCountry(q, p);
    }

    @GetMapping("/all")
    public List<Country> findAllCountry(@RequestParam(value = "q", required = false) String q, Pageable p) {
        q = q == null? "": q.toUpperCase();
        q = "%"+q+"%";
        return countryRepository.findAllCountry(q, p);
    }

    @PostMapping
    public Country create(@RequestBody @Valid Country c) {
        Optional<Country> e = countryRepository.findByCode(c.getCode());
        if(e.isPresent() && e.get().getId() != c.getId()) {
            throw new CodeAlreadyUsedException();
        }
        countryRepository.save(c);
        return c;
    }

    @PutMapping("/{id}")
    public Country updateCountry(@PathVariable("id") Integer id, @RequestBody @Valid Country c) {
        Optional<Country> e = countryRepository.findByCode(c.getCode());
        if(e.isPresent() && e.get().getId() != id) {
            throw new CodeAlreadyUsedException();
        }
        Country exist = countryRepository.findById(id).get();
        if(exist == null) {
            throw new DataNotFoundException("Data negara dengan id tersebut tidak ditemukan");
        }
        exist.setCode(c.getCode());
        exist.setName(c.getName());
        exist.setDescription(c.getDescription());
        countryRepository.save(exist);
        return c;
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable("id") Integer id) {
        Optional<Country> e = countryRepository.findById(id);
        if(!e.isPresent() ) {
            throw new DataNotFoundException("data negara tidak ditemukan");
        }
        countryRepository.deleteById(id);
    }




}
