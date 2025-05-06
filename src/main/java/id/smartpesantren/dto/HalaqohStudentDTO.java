package id.smartpesantren.dto;

import id.smartpesantren.entity.AsramaMappingStudent;

public class HalaqohStudentDTO {
    private String asramaId;
    private String asramaCode;
    private String asramaName;
    private String id;
    private String nis;
    private String nisn;
    private String name;
    private String joinYear;

    public HalaqohStudentDTO() {
    }

    public HalaqohStudentDTO(AsramaMappingStudent a) {
        setId(a.getId());
        setNis(a.getStudent().getNis());
        setNisn(a.getStudent().getNisn());
        setName(a.getStudent().getName());
        setJoinYear(a.getStudent().getJoinYear().getCode());

        setAsramaId(a.getAsramaMapping().getAsrama().getId());
        setAsramaCode(a.getAsramaMapping().getAsrama().getCode());
        setAsramaName(a.getAsramaMapping().getAsrama().getName());
    }

    public String getAsramaId() {
        return asramaId;
    }

    public void setAsramaId(String asramaId) {
        this.asramaId = asramaId;
    }

    public String getAsramaCode() {
        return asramaCode;
    }

    public void setAsramaCode(String asramaCode) {
        this.asramaCode = asramaCode;
    }

    public String getAsramaName() {
        return asramaName;
    }

    public void setAsramaName(String asramaName) {
        this.asramaName = asramaName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJoinYear() {
        return joinYear;
    }

    public void setJoinYear(String joinYear) {
        this.joinYear = joinYear;
    }
}
