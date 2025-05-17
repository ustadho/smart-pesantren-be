package id.smartpesantren.dto;

public interface KBMAssesmentListQuery {
    public Integer getLevel();
    public String getTeacherId();
    public String getSubjectId();
    public String getSubjectName();
    public String getClassRoomId();
    public String getClassRoomName();
    public String getInstitutionId();
    public String getInstitutionName();
    public Integer getStudentCount();
    public Integer getNilaiHarianCount();
    public Integer getNilaiKetrampilanCount();
    public Integer getNilaiSikapCount();
    public Integer getNilaiPtsCount();
    public Integer getNilaiPasCount();
    public Integer getNilaiAkhirCount();
}
