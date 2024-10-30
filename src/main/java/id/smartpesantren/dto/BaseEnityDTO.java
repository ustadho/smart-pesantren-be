package id.smartpesantren.dto;

public class BaseEnityDTO {
    public String id;
    public String name;

    public BaseEnityDTO() {
    }

    public BaseEnityDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
