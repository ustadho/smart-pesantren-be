package id.smartpesantren.web;

import id.smartpesantren.dto.DistrictDTO;
import id.smartpesantren.entity.City;
import id.smartpesantren.entity.District;
import id.smartpesantren.entity.Province;
import id.smartpesantren.repository.DistrictRepository;
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
@RequestMapping("api/district")
public class DistrictResource {
    @Autowired
    DistrictRepository districtRepository;

    @GetMapping
    public Page<DistrictDTO> filterDistrict(@RequestParam(value = "q", required = false) String q, Pageable p) {
        q = q == null? "": q.toUpperCase();
        q = "%"+q+"%";
        return districtRepository.filterDistrict(q, p).map(DistrictDTO::new);
    }

    @GetMapping("/all/{cityId}")
    public List<DistrictDTO> findAllDistrict(@PathVariable("cityId") Integer cid, @RequestParam(value = "q", required = false) String q, Pageable p) {
        q = q == null? "": q.toUpperCase();
        q = "%"+q+"%";
        return districtRepository.findAllDistrict(cid, q, p)
                .stream()
                .map(DistrictDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public DistrictDTO create(@RequestBody @Valid DistrictDTO c) {
        Optional<District> e = districtRepository.findByCode(c.getCode());
        if(e.isPresent() && e.get().getId() != c.getId()) {
            throw new CodeAlreadyUsedException();
        }

        District newDistrict = districtRepository.save(new District(c.getId(), new City(c.getCityId()), c.getCode(), c.getName(), c.getDescription()));
        c.setId(newDistrict.getId());
        return c;
    }

    @PutMapping("/{id}")
    public void updateDistrict(@PathVariable("id") Integer id, @RequestBody @Valid DistrictDTO c) {
        Optional<District> e = districtRepository.findByCode(c.getCode());
        if(e.isPresent() && e.get().getId() != id) {
            throw new CodeAlreadyUsedException();
        }
        District exist = districtRepository.findById(id).get();
        if(exist == null) {
            throw new DataNotFoundException("Data kecamatan dengan id tersebut tidak ditemukan");
        }
        exist.setCity(new City(c.getCityId()));
        exist.setCode(c.getCode());
        exist.setName(c.getName());
        exist.setDescription(c.getDescription());
        districtRepository.save(exist);
    }

    @DeleteMapping("/{id}")
    public void deleteDistrict(@PathVariable("id") Integer id) {
        Optional<District> e = districtRepository.findById(id);
        if(!e.isPresent() ) {
            throw new DataNotFoundException("data kota tidak ditemukan");
        }
        districtRepository.deleteById(id);
    }




}
