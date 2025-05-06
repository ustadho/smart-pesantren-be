package id.smartpesantren.web.rest.vm;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class HalaqohVM {
    private String id;
    @NotNull
    public String pesantrenId;
    public String pesantrenName;

    @NotNull
    public String academicYearId;
    public String academicYearName;


    // Tambah field untuk manyToMany
    private List<String> musyrifIds;

    private String description;

    public List<AsramaMappingVMStudent> students = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getMusyrifIds() {
        return musyrifIds;
    }

    public void setMusyrifIds(List<String> musyrifIds) {
        this.musyrifIds = musyrifIds;
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

    public String getAcademicYearName() {
        return academicYearName;
    }

    public void setAcademicYearName(String academicYearName) {
        this.academicYearName = academicYearName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull String getPesantrenId() {
        return pesantrenId;
    }

    public void setPesantrenId(@NotNull String pesantrenId) {
        this.pesantrenId = pesantrenId;
    }

    public String getPesantrenName() {
        return pesantrenName;
    }

    public void setPesantrenName(String pesantrenName) {
        this.pesantrenName = pesantrenName;
    }
}
