package id.smartpesantren.web;

import id.smartpesantren.dto.CityDTO;
import id.smartpesantren.entity.City;
import id.smartpesantren.entity.Province;
import id.smartpesantren.repository.CityRepository;
import id.smartpesantren.web.rest.errors.CodeAlreadyUsedException;
import id.smartpesantren.web.rest.errors.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/city")
public class CityResource {
    @Autowired
    CityRepository cityRepository;

    @GetMapping
    public Page<CityDTO> filterCity(@RequestParam(value = "q", required = false) String q, Pageable p) {
        q = q == null? "": q.toUpperCase();
        q = "%"+q+"%";
        return cityRepository.filterCity(q, p).map(CityDTO::new);
    }

    @GetMapping("/all/{cityId}")
    public List<CityDTO> findAllCity(@PathVariable("cityId") Integer cid, @RequestParam(value = "q", required = false) String q, Pageable p) {
        q = q == null? "": q.toUpperCase();
        q = "%"+q+"%";
        return cityRepository.findAllCity(cid, q, p)
                .stream()
                .map(CityDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CityDTO create(@RequestBody @Valid CityDTO c) {
        Optional<City> e = cityRepository.findByCode(c.getCode());
        if(e.isPresent() && e.get().getId() != c.getId()) {
            throw new CodeAlreadyUsedException();
        }

        City newCity = cityRepository.save(new City(c.getId(), new Province(c.getProvinceId()), c.getCode(), c.getName(), c.getDescription()));
        c.setId(newCity.getId());
        return c;
    }

    @PutMapping("/{id}")
    public void updateCity(@PathVariable("id") Integer id, @RequestBody @Valid CityDTO c) {
        Optional<City> e = cityRepository.findByCode(c.getCode());
        if(e.isPresent() && e.get().getId() != id) {
            throw new CodeAlreadyUsedException();
        }
        City exist = cityRepository.findById(id).get();
        if(exist == null) {
            throw new DataNotFoundException("Data negara dengan id tersebut tidak ditemukan");
        }
        exist.setProvince(new Province(c.getProvinceId()));
        exist.setCode(c.getCode());
        exist.setName(c.getName());
        exist.setDescription(c.getDescription());
        cityRepository.save(exist);
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable("id") Integer id) {
        Optional<City> e = cityRepository.findById(id);
        if(!e.isPresent() ) {
            throw new DataNotFoundException("data kota tidak ditemukan");
        }
        cityRepository.deleteById(id);
    }




}
