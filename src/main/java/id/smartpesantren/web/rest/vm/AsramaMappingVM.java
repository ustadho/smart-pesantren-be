package id.smartpesantren.web.rest.vm;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class AsramaMappingVM {
    private String id;
    @NotNull
    public String asramaId;

    @NotNull
    public String academicYearId;

    public String musyrifId;

    private String description;

    public List<AsramaMappingVMStudent> students = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsramaId() {
        return asramaId;
    }

    public void setAsramaId(String asramaId) {
        this.asramaId = asramaId;
    }

    public String getMusyrifId() {
        return musyrifId;
    }

    public void setMusyrifId(String musyrifId) {
        this.musyrifId = musyrifId;
    }

    public List<AsramaMappingVMStudent> getStudents() {
        return students;
    }

    public void setStudents(List<AsramaMappingVMStudent> students) {
        this.students = students;
    }

    public String getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(String academicYearId) {
        this.academicYearId = academicYearId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
