package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.Building;

public class BuildingVM {
    private String id;
    private String code;
    private String name;
    private String description;
    private String color;
    private String locationId;
    private String locationName;


    public BuildingVM() {
    }

    public BuildingVM(Building b) {
        setId(b.getId());
        setCode(b.getCode());
        setName(b.getName());
        setDescription(b.getDescription());
        setColor(b.getColor());
        setLocationId(b.getLocation() == null? null: b.getLocation().getId());
        setLocationName(b.getLocation() == null? null: b.getLocation().getName());
    }

    public BuildingVM(String id, String code, String name, String description, String color, String locationId, String locationName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.color = color;
        this.setLocationId(locationId);
        this.setLocationName(locationName);
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
