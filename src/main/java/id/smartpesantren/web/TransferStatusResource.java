package id.smartpesantren.web;

import id.smartpesantren.entity.HRTransferType;
import id.smartpesantren.entity.MaritalStatus;
import id.smartpesantren.repository.HRTransferTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hr/transfer-type")
public class TransferStatusResource {
    @Autowired
    HRTransferTypeRepository repository;

    @GetMapping("all")
    public List<HRTransferType> findAll() {
        return repository.findAll();
    }
}
