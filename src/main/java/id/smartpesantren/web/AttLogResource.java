package id.smartpesantren.web;

import id.smartpesantren.entity.AttLog;
import id.smartpesantren.repository.AttLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/presence/att-log")
public class AttLogResource {
    @Autowired
    AttLogRepository repository;

    @GetMapping
    public Page<AttLog> findAllByDate(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                      Pageable pageable) {
        return repository.findAllByDate(startDate, endDate, pageable);
    }
}
