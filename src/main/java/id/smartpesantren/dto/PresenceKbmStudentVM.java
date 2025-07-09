package id.smartpesantren.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class PresenceKbmStudentVM {
    private List<PresenceKbmStudentDetailVM> details = new ArrayList<>();
    @NotNull
    @NotEmpty
    private String id;
    private int studentCount;
    private int alphaCount;
    private int izinCount;
    private int sakitCount;

    public PresenceKbmStudentVM() {
    }

    public PresenceKbmStudentVM(List<PresenceKbmStudentDetailVM> details, String id, int studentCount, int alphaCount, int izinCount, int sakitCount) {
        this.details = details;
        this.id = id;
        this.studentCount = studentCount;
        this.alphaCount = alphaCount;
        this.izinCount = izinCount;
        this.sakitCount = sakitCount;
    }

    public List<PresenceKbmStudentDetailVM> getDetails() {
        return details;
    }

    public void setDetails(List<PresenceKbmStudentDetailVM> details) {
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getAlphaCount() {
        return alphaCount;
    }

    public void setAlphaCount(int alphaCount) {
        this.alphaCount = alphaCount;
    }

    public int getIzinCount() {
        return izinCount;
    }

    public void setIzinCount(int izinCount) {
        this.izinCount = izinCount;
    }

    public int getSakitCount() {
        return sakitCount;
    }

    public void setSakitCount(int sakitCount) {
        this.sakitCount = sakitCount;
    }
}
