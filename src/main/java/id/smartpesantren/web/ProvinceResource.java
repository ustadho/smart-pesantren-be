package id.smartpesantren.web;

import id.smartpesantren.dto.ProvinceDTO;
import id.smartpesantren.entity.Country;
import id.smartpesantren.entity.Province;
import id.smartpesantren.repository.ProvinceRepository;
import id.smartpesantren.web.rest.errors.CodeAlreadyUsedException;
import id.smartpesantren.web.rest.errors.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/province")
public class ProvinceResource {
    @Autowired
    ProvinceRepository provinceRepository;

    @GetMapping
    public Page<Province> filterProvince(@RequestParam(value = "q", required = false) String q, Pageable p) {
        q = q == null? "": q.toUpperCase();
        q = "%"+q+"%";
        return provinceRepository.filterProvince(q, p);
    }

    @GetMapping("/all/{countryId}")
    public List<Province> findAllCountry(@PathVariable("countryId") Integer cid, @RequestParam(value = "q", required = false) String q, Pageable p) {
        q = q == null? "": q.toUpperCase();
        q = "%"+q+"%";
        return provinceRepository.findAllProvince(cid, q, p);
    }

    @PostMapping
    public ProvinceDTO create(@RequestBody @Valid ProvinceDTO c) {
        Optional<Province> e = provinceRepository.findByCode(c.getCode());
        if(e.isPresent() && e.get().getId() != c.getId()) {
            throw new CodeAlreadyUsedException();
        }

        Province newProvince = provinceRepository.save(new Province(c.getId(), new Country(c.getCountryId()), c.getCode(), c.getName(), c.getDescription()));
        c.setId(newProvince.getId());
        return c;
    }

    @PutMapping("/{id}")
    public void updateCountry(@PathVariable("id") Integer id, @RequestBody @Valid ProvinceDTO c) {
        Optional<Province> e = provinceRepository.findByCode(c.getCode());
        if(e.isPresent() && e.get().getId() != id) {
            throw new CodeAlreadyUsedException();
        }
        Province exist = provinceRepository.findById(id).get();
        if(exist == null) {
            throw new DataNotFoundException("Data negara dengan id tersebut tidak ditemukan");
        }
        exist.setCountry(new Country(c.getCountryId()));
        exist.setCode(c.getCode());
        exist.setName(c.getName());
        exist.setDescription(c.getDescription());
        provinceRepository.save(exist);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable("id") Integer id) {
        Optional<Province> e = provinceRepository.findById(id);
        if(!e.isPresent() ) {
            throw new DataNotFoundException("data provinsi tidak ditemukan");
        }
        provinceRepository.deleteById(id);
    }




}
