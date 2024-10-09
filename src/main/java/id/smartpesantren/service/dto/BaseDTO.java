package id.smartpesantren.service.dto;

public class BaseDTO {
    private Integer id;
    private String nama;

    public BaseDTO(Integer id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public BaseDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
