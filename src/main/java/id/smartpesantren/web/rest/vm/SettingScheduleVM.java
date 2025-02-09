package id.smartpesantren.web.rest.vm;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SettingScheduleVM {
    private String id;

    @NotEmpty @NotNull
    private String academicYearId;

    @NotEmpty @NotNull
    private String institutionId;
    private String sex;
    private String description;
    private List<SettingScheduleVMTeacher> teachers = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(String academicYearId) {
        this.academicYearId = academicYearId;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SettingScheduleVMTeacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<SettingScheduleVMTeacher> teachers) {
        this.teachers = teachers;
    }
}
