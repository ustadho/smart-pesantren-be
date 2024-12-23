package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.Building;

public class BuildingVM {
    private String id;
    private String code;
    private String name;
    private String description;
    private String color;

    public BuildingVM() {
    }

    public BuildingVM(Building b) {
        setId(b.getId());
        setCode(b.getCode());
        setName(b.getName());
        setDescription(b.getDescription());
        setColor(b.getColor());
    }

    public BuildingVM(String id, String code, String name, String description, String color) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.color = color;
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
}
