package id.smartpesantren.web;

import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.PresenceStatus;
import id.smartpesantren.entity.Student;
import id.smartpesantren.entity.StudentPresence;
import id.smartpesantren.repository.StudentPresenceRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.StudentPresenceVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("api/student-presence")
public class StudentPresenceResource {
    @Autowired
    StudentPresenceRepository studentPresenceRepository;

    @PostMapping
    public StudentPresenceVM createStudentPresence(@RequestBody @Valid StudentPresenceVM vm) {
        StudentPresence sp = fromVM(vm);
        sp = studentPresenceRepository.save(sp);
        return vm;
    }

    @GetMapping
    public Page<StudentPresenceVM> filter(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
            @RequestParam("studentId") String studentId, @RequestParam("presenceStatusId") String presenceStatusId, Pageable p) throws ParseException {
            if (startDate != null && endDate != null) {
            }   
        return studentPresenceRepository.filter(
                startDate.equalsIgnoreCase("")? null: format.parse(startDate),
                endDate.equalsIgnoreCase("")? null: format.parse(endDate),
                presenceStatusId.equalsIgnoreCase("") || presenceStatusId.equalsIgnoreCase("0")? null: Integer.parseInt(presenceStatusId),
                studentId.equalsIgnoreCase("")? null: studentId,
                p
        );
    }

    public StudentPresence fromVM(StudentPresenceVM vm) {
        StudentPresence s = new StudentPresence();
        s.setId(vm.getId());
        s.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        s.setStudent(new Student(vm.getStudentId()));
        s.setDate(vm.getDate());
        s.setPresenceStatus(new PresenceStatus(vm.getPresenceStatusId()));
        s.setAttachment(vm.getAttachment());
        s.setNote(vm.getNote());
        return s;
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
}
