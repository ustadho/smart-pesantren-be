package id.smartpesantren.dto;

import id.smartpesantren.entity.City;
import id.smartpesantren.entity.ReferalInstitution;

public class ReferalInstitutionDTO {
    private String id;
    private String name;

    private String address;

    private Integer subdistrictId;
    private String subdistrictName;

    private Integer cityId;
    private String cityName;
    private String description;
    private Integer educationLevelId;
    private String educationLevelName;

    public ReferalInstitutionDTO() {
    }

    public ReferalInstitutionDTO(ReferalInstitution a) {
        this.id = a.getId();
        this.name = a.getName();
        this.address = a.getAddress();
        this.subdistrictId = a.getSubdistrict() == null? null: a.getSubdistrict().getId();
        this.subdistrictName = a.getSubdistrict() == null? null: a.getSubdistrict().getName();
        this.cityId = a.getCity() == null? null: a.getCity().getId();
        this.cityName = a.getCity() == null? null: a.getCity().getName();
        this.description = a.getDescription();
        this.setEducationLevelId(a.getEducationLevel() == null? null: a.getEducationLevel().getId());
        this.setEducationLevelName(a.getEducationLevel() == null? null: a.getEducationLevel().getName());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEducationLevelId() {
        return educationLevelId;
    }

    public void setEducationLevelId(Integer educationLevelId) {
        this.educationLevelId = educationLevelId;
    }

    public String getEducationLevelName() {
        return educationLevelName;
    }

    public void setEducationLevelName(String educationLevelName) {
        this.educationLevelName = educationLevelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSubdistrictId() {
        return subdistrictId;
    }

    public void setSubdistrictId(Integer subdistrictId) {
        this.subdistrictId = subdistrictId;
    }

    public String getSubdistrictName() {
        return subdistrictName;
    }

    public void setSubdistrictName(String subdistrictName) {
        this.subdistrictName = subdistrictName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
