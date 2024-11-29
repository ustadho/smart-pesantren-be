package id.smartpesantren.web.rest.vm;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ClassRoomStudentVM {
    @NotNull
    public String classRoomId;

    public List<ClassRoomStudentVMDetail> students = new ArrayList<>();

    public String getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(String classRoomId) {
        this.classRoomId = classRoomId;
    }

    public List<ClassRoomStudentVMDetail> getStudents() {
        return students;
    }

    public void setStudents(List<ClassRoomStudentVMDetail> students) {
        this.students = students;
    }
}
