package id.smartpesantren.dto;

public interface ScheduleTeacherSubjectListQuery {
    String getInstitutionName();
    String getClassRoomId();
    String getClassRoomName();
    String getSex();
    String getSubjectId();
    String getSubjectName();
    Integer getJumlahJam();
    Integer getJumlahJadwal();
    String getDays();
}
