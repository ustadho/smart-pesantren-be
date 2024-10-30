package id.smartpesantren.web;

import id.smartpesantren.dto.LocationDTO;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.Location;
import id.smartpesantren.repository.LocationRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/setting/location")
public class LocationResource {
    @Autowired
    LocationRepository locationRepository;

    @PostMapping
    public void createJobLevel(@RequestBody @Valid LocationDTO req) {
        Location o = new Location();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(req.getCode());
        o.setName(req.getName());
        o.setDescription(req.getDescription());
        o.setLatLong(req.getLatLong());
        locationRepository.save(o);
    }

    @GetMapping
    Page<LocationDTO> filter(@RequestParam("q") String q, Pageable p) {
        return locationRepository.filter("%"+q.toUpperCase()+"%", p);

    }

    @GetMapping("all")
    Iterable<LocationDTO> findAllOrganization(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return locationRepository.findAllLocation("%"+q.toUpperCase()+"%");
    }

    @GetMapping("{id}")
    LocationDTO findById(@PathVariable("id") String id) {
        return locationRepository.findById(id)
                .map(LocationDTO::new)
                .orElseThrow(() -> new InternalServerErrorException("Job Level could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody LocationDTO req) {
        Optional<Location> a = locationRepository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("Unit dengan id tersebut tidak ditemukan");
        }
        Location data = a.get();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setDescription(req.getDescription());
        data.setLatLong(req.getLatLong());
        locationRepository.save(data);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") String id) {
        locationRepository.deleteById(id);
    }
}
