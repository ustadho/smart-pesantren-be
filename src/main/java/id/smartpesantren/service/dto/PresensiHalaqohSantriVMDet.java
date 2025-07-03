package id.smartpesantren.service.dto;

public class PresensiHalaqohSantriVMDet {
    private String id;
    private String santriId;
    private String santriName;
    private String classRoom;
    private String photo;
    private Integer statusId;
    private String statusName;
    private String catatan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSantriId() {
        return santriId;
    }

    public void setSantriId(String santriId) {
        this.santriId = santriId;
    }

    public String getSantriName() {
        return santriName;
    }

    public void setSantriName(String santriName) {
        this.santriName = santriName;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
