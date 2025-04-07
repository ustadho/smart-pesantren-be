package id.smartpesantren.dto;

public interface ScheduleTeacherPresenceDTO {
    String getScheduleId();
    int getDayId();
    String getDayName();
    String getClassRoomId();
    String getClassRoomName();
    String getInstitutionName();
    String getSubjectId();
    String getSubjectName();
    String getStartTime();
    String getEndTime();
    int getDuration();
    String getTeacherId();
    String getTeacherName();
    int getSeq();
    String getId();
    String getPresenceId();
    String getMateri();
    String getPencapaian();
    String getAttachment();
    String getFotoAbsen();
    String getCreatedBy();
    String getCreatedDate();
    Integer getPresenceStatusId();
    String getPresenceStatusName();
    Integer getStudentCount();
}
