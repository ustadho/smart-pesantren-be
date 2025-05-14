package id.smartpesantren.web;

import id.smartpesantren.dto.KBMAssesmentListQuery;
import id.smartpesantren.dto.KBMAssesmentStudentQuery;
import id.smartpesantren.repository.KBMAssesmentRepository;
import id.smartpesantren.service.KBMAssesmentService;
import id.smartpesantren.service.dto.KBMAssesmentVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/kbm-assesment")
public class KBMAssesmentResource {
    @Autowired
    KBMAssesmentService service;

    @Autowired
    KBMAssesmentRepository repository;

    @PostMapping
    public KBMAssesmentVM createKBMAssesment(@RequestBody @Valid KBMAssesmentVM vm) {
        return service.createOrUpdate(vm);
    }

    @PutMapping("/{id}")
    public KBMAssesmentVM updateKBMAssesment(@PathVariable("id") String id, @RequestBody @Valid KBMAssesmentVM vm) {
        return service.createOrUpdate(vm);
    }

    @GetMapping("/class-room")
    public List<KBMAssesmentStudentQuery> findKBMAssesmentByClassRoomId(@RequestParam("classRoomId") String classRoomId, @RequestParam("semester") Integer semester, @RequestParam(value = "subjectId", defaultValue = "") String subjectId) {
        List<KBMAssesmentStudentQuery> list =  repository.findKBMAssesmentStudentQueryByClassRoomId(classRoomId, semester, subjectId);
        return list;
    }

    @GetMapping("/list-by-academic-teacher")
    List<KBMAssesmentListQuery> findAssesmentListByYearAndTeacher(@RequestParam("academicYearId") String academicYearId,
                                                                  @RequestParam("teacherId") String teacherId,
                                                                  @RequestParam("semester") Integer semester) {
        return repository.findAssesmentListByYearAndTeacherAndSemester(academicYearId, teacherId, semester);
    }
}
