package id.smartpesantren.web;

import id.smartpesantren.dto.OrganizationDTO;
import id.smartpesantren.dto.OrganizationTreeDTO;
import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.Organization;
import id.smartpesantren.repository.OrganizationRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/hr/organization")
public class OrganizationResource {
    @Autowired
    OrganizationRepository organizationRepository;

    @PostMapping
    public void createOrganization(@RequestBody @Valid OrganizationDTO req) {
        Organization o = new Organization();
        o.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        o.setCode(req.getCode());
        o.setName(req.getName());
        o.setDescription(req.getDescription());
        o.setParent(req.getParentId() == null? null: new Organization(req.getParentId()));
        o.setActive(req.getActive());
        organizationRepository.save(o);
    }

    @GetMapping
    Page<OrganizationDTO> filter(@RequestParam("q") String q, Pageable p) {
        return organizationRepository.findAllOrganization("%"+q.toUpperCase()+"%", p);

    }

    @GetMapping("all")
    Iterable<OrganizationDTO> findAllOrganization(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return organizationRepository.findAllOrganization("%"+q.toUpperCase()+"%");
    }

    @GetMapping("all-tree")
    Iterable<OrganizationTreeDTO> findAllOrganizationTree() {
        return organizationRepository.findAllOrganizationTree();
    }

    @GetMapping("{id}")
    OrganizationDTO findById(@PathVariable("id") String id) {
        return organizationRepository.findById(id)
                .map(OrganizationDTO::new)
                .orElseThrow(() -> new InternalServerErrorException("Organization could not be found"));
    }

    @PutMapping("{id}")
    void update(@PathVariable("id") String id, @RequestBody OrganizationDTO req) {
        Optional<Organization> a = organizationRepository.findById(id);
        if (!a.isPresent()) {
            throw new InternalServerErrorException("Unit dengan id tersebut tidak ditemukan");
        }
        Organization data = a.get();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setDescription(req.getDescription());
        data.setParent(req.getParentId() == null? null: new Organization(req.getParentId()));
        data.setActive(req.getActive());
        organizationRepository.save(data);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") String id) {
        organizationRepository.deleteById(id);
    }
}
