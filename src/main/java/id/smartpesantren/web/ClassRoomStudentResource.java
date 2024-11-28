package id.smartpesantren.web;

import id.smartpesantren.dto.ClassRoomStudentDTO;
import id.smartpesantren.entity.ClassRoom;
import id.smartpesantren.entity.ClassRoomStudent;
import id.smartpesantren.entity.Student;
import id.smartpesantren.repository.ClassRoomRepository;
import id.smartpesantren.repository.ClassRoomStudentRepository;
import id.smartpesantren.service.ClassRoomStudentService;
import id.smartpesantren.web.rest.errors.DataNotFoundException;
import id.smartpesantren.web.rest.vm.ClassRoomStudentVM;
import id.smartpesantren.web.rest.vm.ClassRoomStudentVMDetail;
import id.smartpesantren.web.rest.vm.ClassRoomVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/academic/class-room-student")
public class ClassRoomStudentResource {
    @Autowired
    ClassRoomStudentRepository repository;

    @Autowired
    ClassRoomRepository classRoomRepository;

    @Autowired
    ClassRoomStudentService service;

    @GetMapping("/{id}")
    public ClassRoomStudentVM findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @PutMapping
    @Transactional
    public void save(@RequestBody @Valid ClassRoomStudentVM vm) {
        //Cek dulu sebelum diinsert
        Optional<ClassRoom> cr = classRoomRepository.findById(vm.getClassRoomId());
        if(!cr.isPresent()) {
            throw new DataNotFoundException("Tahun akademik tidak dikenal");
        }
        for (ClassRoomStudentVMDetail d: vm.getStudents()) {
            if(d.getId() == null) {
                ClassRoomStudent cs = repository.findByStudentAndAcademicYear(d.getStudentId(), cr.get().getAcademicYear().getId());
                if (cs != null) {
                    throw new DuplicateKeyException("Santri tersebut sudah dimasukkan di kelas: " + cs.getClassRoom().getName());
                }
            }
        }
        service.save(vm);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        repository.deleteById(id);
    }

}
