package id.smartpesantren.dto;

public interface KBMAssesmentListQuery {
    public Integer getLevel();
    public String getTeacherId();
    public String getSubjectId();
    public String getSubjectName();
    public String getClassRoomId();
    public String getClassRoomName();
    public String getInstitutionName();
    public Integer getStudentCount();
    public Integer getNilaiTugasCount();
    public Integer getNilaiUTSCount();
    public Integer getNilaiUASCount();
    public Integer getNilaiAkhirCount();
}
