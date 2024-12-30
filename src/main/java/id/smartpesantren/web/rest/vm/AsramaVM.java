package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.Asrama;

public class AsramaVM {
    private String id;
    private String code;
    private String name;
    private String description;
    private String buildingId;
    private String buildingName;
    private String pesantrenId;
    private String pesantrenName;
    private String locationName;
    private String sex;
    private Short capacity;


    public AsramaVM() {
    }

    public AsramaVM(Asrama a) {
        setId(a.getId());
        setCode(a.getCode());
        setName(a.getName());
        setDescription(a.getDescription());
        setBuildingId(a.getBuilding() == null? null: a.getBuilding().getId());
        setBuildingName(a.getBuilding() == null? null: a.getBuilding().getName());
        setLocationName(a.getBuilding() == null? null: a.getBuilding().getLocation().getName());
        setPesantrenId(a.getPesantren() == null? null: a.getPesantren().getId());
        setPesantrenName(a.getPesantren() == null? null: a.getPesantren().getName());
        setSex(a.getSex());
        setCapacity(a.getCapacity());
    }

    public AsramaVM(String id, String code, String name, String description, String buildingId, String buildingName,
                    String locationName, String sex, Short capacity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.locationName = locationName;
        this.sex = sex;
        this.capacity = capacity;
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

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getPesantrenId() {
        return pesantrenId;
    }

    public void setPesantrenId(String pesantrenId) {
        this.pesantrenId = pesantrenId;
    }

    public String getPesantrenName() {
        return pesantrenName;
    }

    public void setPesantrenName(String pesantrenName) {
        this.pesantrenName = pesantrenName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Short getCapacity() {
        return capacity;
    }

    public void setCapacity(Short capacity) {
        this.capacity = capacity;
    }
}
