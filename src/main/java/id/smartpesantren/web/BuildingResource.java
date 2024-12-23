package id.smartpesantren.web;

import id.smartpesantren.entity.Building;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.Location;
import id.smartpesantren.repository.BuildingRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.BuildingVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/setting/building")
public class BuildingResource {
    @Autowired
    BuildingRepository buildingRepository;

    @PostMapping
    public void createBuilding(@RequestBody @Valid BuildingVM vm) {
        Building o = new Building();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(vm.getCode());
        o.setName(vm.getName());
        o.setDescription(vm.getDescription());
        o.setColor(vm.getColor());
        o.setLocation(new Location(vm.getLocationId()));
        buildingRepository.save(o);
    }

    @GetMapping
    Page<BuildingVM> filter(@RequestParam("locationId") String locationId, @RequestParam("q") String q, Pageable p) {
        return buildingRepository.filter(locationId, "%"+q.toUpperCase()+"%", p);
    }

    @GetMapping("all")
    Iterable<BuildingVM> findAllOrganization(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return buildingRepository.findAllBuilding("%"+q.toUpperCase()+"%");
    }

    @GetMapping("{id}")
    BuildingVM findById(@PathVariable("id") String id) {
        return buildingRepository.findById(id)
                .map(BuildingVM::new)
                .orElseThrow(() -> new InternalServerErrorException("Job Level could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody BuildingVM vm) {
        Optional<Building> a = buildingRepository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("Unit dengan id tersebut tidak ditemukan");
        }
        Building data = a.get();
        data.setCode(vm.getCode());
        data.setName(vm.getName());
        data.setDescription(vm.getDescription());
        data.setColor(vm.getColor());
        data.setLocation(new Location(vm.getLocationId()));
        buildingRepository.save(data);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") String id) {
        buildingRepository.deleteById(id);
    }
}
