package id.smartpesantren.web;

import id.smartpesantren.entity.Foundation;
import id.smartpesantren.entity.PresenceStatus;
import id.smartpesantren.entity.Student;
import id.smartpesantren.entity.StudentPresence;
import id.smartpesantren.repository.StudentPresenceRepository;
import id.smartpesantren.security.SecurityUtils;
import id.smartpesantren.web.rest.vm.StudentPresenceVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
