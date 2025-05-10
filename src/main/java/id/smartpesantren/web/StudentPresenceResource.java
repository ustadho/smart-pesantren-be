package id.smartpesantren.web;

import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.PresenceStatus;
import id.smartpesantren.entity.Student;
import id.smartpesantren.entity.StudentPresence;
import id.smartpesantren.repository.StudentPresenceRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.errors.InternalServerErrorException;
import id.smartpesantren.web.rest.vm.StudentPresenceVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

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

    @PutMapping("{id}")
    public StudentPresenceVM updateStudentPresence(@PathVariable("id") String id, @RequestBody @Valid StudentPresenceVM vm) {
        StudentPresence sp = fromVM(vm);
        sp = studentPresenceRepository.save(sp);
        return vm;
    }

    @GetMapping("{id}")
    public StudentPresenceVM findById(@PathVariable("id") String id) {
        Optional<StudentPresence> optionalPresence = studentPresenceRepository.findById(id);
        if (optionalPresence.isPresent()) {
            return new StudentPresenceVM(optionalPresence.get());
        }
        return null;
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
        StudentPresence s = null;
        if(vm.getId() != null) {
            Optional<StudentPresence> optionalPresence = studentPresenceRepository.findById(vm.getId());
            if (optionalPresence.isPresent()) {
                s = optionalPresence.get();
            }
        } else {
            s = new StudentPresence();
        }
        s.setId(vm.getId());
        s.setFoundation(new Foundation(SecurityUtils.getFoundationId().get()));
        s.setStudent(new Student(vm.getStudentId()));
        s.setDate(vm.getDate());
        s.setNumDays(vm.getNumDays());
        s.setPresenceStatus(new PresenceStatus(vm.getPresenceStatusId()));
        s.setAttachment(vm.getAttachment());
        s.setNote(vm.getNote());
        return s;
    }

    private StudentPresenceVM toVM(StudentPresence s) {
        return new StudentPresenceVM(s);
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
}
