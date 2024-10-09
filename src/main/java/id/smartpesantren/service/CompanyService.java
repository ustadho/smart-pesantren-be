package id.smartpesantren.service;

import id.smartpesantren.repository.FoundationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final Logger log = LoggerFactory.getLogger(CompanyService.class);

    private final FoundationRepository foundationRepository;

    public CompanyService(FoundationRepository foundationRepository) {
        this.foundationRepository = foundationRepository;
    }

}
