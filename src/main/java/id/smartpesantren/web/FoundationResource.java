package id.smartpesantren.web;

import id.smartpesantren.entity.Foundation;
import id.smartpesantren.repository.FoundationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/setting/foundation")
public class FoundationResource {
    @Autowired
    FoundationRepository foundationRepository;

    @GetMapping
    public Foundation getFoundation() {
        return foundationRepository.findTop1ByOrderById().get();
    }

    @PostMapping
    public Foundation create(@RequestBody @Valid Foundation f) {
        f.setId(1);
        foundationRepository.save(f);
        return f;
    }

    @PutMapping
    public void updateCountry(@RequestBody @Valid Foundation c) {
        Optional<Foundation> e = foundationRepository.findTop1ByOrderById();
        Foundation exist = null;
        if(e.isPresent()) {
            exist = e.get();
        }

        exist.setName(c.getName());
        exist.setActive(c.getActive());
        exist.setAddress1(c.getAddress1());
        exist.setAddress2(c.getAddress2());
        exist.setAddress3(c.getAddress3());
        exist.setCountry(c.getCountry());
        exist.setPhone(c.getPhone());
        exist.setStartDate(c.getStartDate());
        exist.setTaxBranchName(c.getTaxBranchName());
        exist.setTaxKluSpt(c.getTaxKluSpt());
        exist.setTaxNpwp(c.getTaxNpwp());
        exist.setTaxPkp(c.getTaxPkp());
        exist.setTaxAddress1(c.getTaxAddress1());
        exist.setTaxAddress2(c.getTaxAddress2());
        exist.setTaxAddress3(c.getTaxAddress3());
        exist.setTaxSerialNo(c.getTaxSerialNo());
        exist.setTaxTglPengukuhan(c.getTaxTglPengukuhan());
        foundationRepository.save(exist);
    }
}
