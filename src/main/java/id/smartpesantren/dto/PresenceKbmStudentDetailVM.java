package id.smartpesantren.dto;

public class PresenceKbmStudentDetailVM {
    private String note;
    private String classRoom;
    private String id;
    private String photo;
    private String studentId;
    private String studentName;
    private Integer statusId;
    private String statusName;

    public PresenceKbmStudentDetailVM() {
    }

    public PresenceKbmStudentDetailVM(String note, String classRoom, String id, String photo, String studentId, String studentName, Integer statusId, String statusName) {
        this.note = note;
        this.classRoom = classRoom;
        this.id = id;
        this.photo = photo;
        this.studentId = studentId;
        this.studentName = studentName;
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public PresenceKbmStudentDetailVM copyWith(String note, String classRoom, String id, String photo, String studentId, String studentName, Integer statusId, String statusName) {
        return new PresenceKbmStudentDetailVM(
                note != null ? note : this.note,
                classRoom != null ? classRoom : this.classRoom,
                id != null ? id : this.id,
                photo != null ? photo : this.photo,
                studentId != null ? studentId : this.studentId,
                studentName != null ? studentName : this.studentName,
                statusId != null ? statusId : this.statusId,
                statusName != null ? statusName : this.statusName
        );
    }
}

