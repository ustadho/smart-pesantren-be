package id.smartpesantren.web;

import id.smartpesantren.dto.SantriListDTO;
import id.smartpesantren.entity.*;
import id.smartpesantren.repository.AsramaRepository;
import id.smartpesantren.repository.AsramaMappingRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.AsramaVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/setting/asrama")
public class AsramaResource {
    @Autowired
    AsramaRepository asramaRepository;

    @Autowired
    AsramaMappingRepository asramaMappingRepository;

    @PostMapping
    public void createAsrama(@RequestBody @Valid AsramaVM vm) {
        Asrama o = new Asrama();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(vm.getCode());
        o.setName(vm.getName());
        o.setDescription(vm.getDescription());
        o.setBuilding(new Building(vm.getBuildingId()));
        o.setSex(vm.getSex());
        o.setCapacity(vm.getCapacity());
        asramaRepository.save(o);
    }

    @GetMapping
    Page<AsramaVM> filter(@RequestParam("locationId") String locationId,
                          @RequestParam("buildingId") String buildingId,
                          @RequestParam("q") String q, Pageable p) {
        return asramaRepository.filter(locationId, buildingId, "%"+q.toUpperCase()+"%", p);
    }

    @GetMapping("all")
    Iterable<AsramaVM> findAll(@RequestParam("locationId") String locationId,
                               @RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return asramaRepository.findAllAsrama(locationId, "%"+q.toUpperCase()+"%");
    }

    @GetMapping("{id}")
    AsramaVM findById(@PathVariable("id") String id) {
        return asramaRepository.findById(id)
                .map(AsramaVM::new)
                .orElseThrow(() -> new InternalServerErrorException("Asrama could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody AsramaVM vm) {
        Optional<Asrama> a = asramaRepository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("Unit dengan id tersebut tidak ditemukan");
        }
        Asrama data = a.get();
        data.setCode(vm.getCode());
        data.setName(vm.getName());
        data.setDescription(vm.getDescription());
        data.setBuilding(new Building(vm.getBuildingId()));
        data.setSex(vm.getSex());
        data.setCapacity(vm.getCapacity());
        asramaRepository.save(data);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") String id) {
        asramaRepository.deleteById(id);
    }
}
