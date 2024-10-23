package id.smartpesantren.dto;

import id.smartpesantren.entity.Location;

public class LocationDTO {
    private String id;
    private String code;
    private String name;
    private String description;
    private String latLong;

    public LocationDTO() {
    }

    public LocationDTO(Location l) {
        this(l.getId(), l.getCode(), l.getName(), l.getDescription(), l.getLatLong());
    }

    public LocationDTO(String id, String code, String name, String description, String latLong) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.latLong = latLong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatLong() {
        return latLong;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }
}
