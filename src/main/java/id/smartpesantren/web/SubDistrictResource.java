package id.smartpesantren.web;

import id.smartpesantren.dto.CityDTO;
import id.smartpesantren.dto.DistrictDTO;
import id.smartpesantren.dto.RegionDTO;
import id.smartpesantren.dto.SubDistrictDTO;
import id.smartpesantren.entity.City;
import id.smartpesantren.entity.District;
import id.smartpesantren.entity.SubDistrict;
import id.smartpesantren.repository.SubDistrictRepository;
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
@RequestMapping("api/sub-district")
public class SubDistrictResource {
    @Autowired
    SubDistrictRepository subDistrictRepository;

    @GetMapping
    public Page<SubDistrictDTO> filterDistrict(@RequestParam(value = "q", required = false) String q, Pageable p) {
        q = q == null? "": q.toUpperCase();
        q = "%"+q+"%";
        return subDistrictRepository.filterSubDistrict(q, p).map(SubDistrictDTO::new);
    }

    @GetMapping("/{id}")
    public SubDistrictDTO findById(@PathVariable("id") Integer id) {
        return subDistrictRepository.findById(id).map(SubDistrictDTO::new).get();
    }

    @GetMapping("/all")
    public List<SubDistrictDTO> findAllDistrict(@RequestParam(value = "did", required = false) Integer cid, @RequestParam(value = "q", required = false) String q, Pageable p) {
        cid = cid == null? 0: cid;
        q = q == null? "": q.toUpperCase();
        q = "%"+q+"%";
        return subDistrictRepository.findAllSubDistrict(cid, q, p)
                .stream()
                .map(SubDistrictDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<RegionDTO> findAllLocation(@RequestParam(value = "q", required = false) String q, @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        q = "%"+q+"%";
        return subDistrictRepository.findAllLocation(q, limit);
    }

    @PostMapping
    public SubDistrictDTO create(@RequestBody @Valid SubDistrictDTO c) {
        Optional<SubDistrict> e = subDistrictRepository.findByCode(c.getCode());
        if(e.isPresent() && e.get().getId() != c.getId()) {
            throw new CodeAlreadyUsedException();
        }

        SubDistrict d = subDistrictRepository.save(new SubDistrict(c.getId(), new District(c.getDistrictId()), c.getCode(), c.getName(), c.getDescription()));
        c.setId(d.getId());
        return c;
    }

    @PutMapping("/{id}")
    public void updateDistrict(@PathVariable("id") Integer id, @RequestBody @Valid SubDistrictDTO
            c) {
        Optional<SubDistrict> e = subDistrictRepository.findByCode(c.getCode());
        if(e.isPresent() && e.get().getId() != id) {
            throw new CodeAlreadyUsedException();
        }
        SubDistrict exist = subDistrictRepository.findById(id).get();
        if(exist == null) {
            throw new DataNotFoundException("Data kecamatan dengan id tersebut tidak ditemukan");
        }
        exist.setDistrict(new District(c.getDistrictId()));
        exist.setCode(c.getCode());
        exist.setName(c.getName());
        exist.setDescription(c.getDescription());
        subDistrictRepository.save(exist);
    }

    @DeleteMapping("/{id}")
    public void deleteSubDistrict(@PathVariable("id") Integer id) {
        Optional<SubDistrict> e = subDistrictRepository.findById(id);
        if(!e.isPresent() ) {
            throw new DataNotFoundException("data kelurahan tidak ditemukan");
        }
        subDistrictRepository.deleteById(id);
    }




}
