package id.smartpesantren.web.rest.vm;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ClassRoomStudentVM {
    @NotNull
    public String classRoomId;
    public String classRoomName;
    private Integer targetTahfidzId;
    private String targetTahfidzDesc;


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

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public Integer getTargetTahfidzId() {
        return targetTahfidzId;
    }

    public void setTargetTahfidzId(Integer targetTahfidzId) {
        this.targetTahfidzId = targetTahfidzId;
    }

    public String getTargetTahfidzDesc() {
        return targetTahfidzDesc;
    }

    public void setTargetTahfidzDesc(String targetTahfidzDesc) {
        this.targetTahfidzDesc = targetTahfidzDesc;
    }
}
