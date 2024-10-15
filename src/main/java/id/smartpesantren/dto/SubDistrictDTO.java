package id.smartpesantren.dto;

import id.smartpesantren.entity.SubDistrict;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SubDistrictDTO {
    private Integer id;
    @NotNull @NotEmpty
    private String code;
    @NotNull @NotEmpty
    private String name;
    private String description;
    @NotNull
    private Integer districtId;
    private String districtName;
    private Integer cityId;
    private String cityName;
    private Integer provinceId;
    private String provinceName;
    private String country;

    public SubDistrictDTO() {
    }

    public SubDistrictDTO(SubDistrict c) {
        this(c.getId(), c.getCode(), c.getName(), c.getDescription(), c.getDistrict().getId(),
                c.getDistrict().getName(),
                c.getDistrict().getCity().getId(), c.getDistrict().getCity().getName(),
                c.getDistrict().getCity().getProvince().getId(), c.getDistrict().getCity().getProvince().getName(),
                c.getDistrict().getCity().getProvince().getCountry().getName()
            );
    }

    public SubDistrictDTO(Integer id, String code, String name, String description, Integer districtId, String districtName, Integer cityId, String cityName, Integer provinceId, String provinceName, String country) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.districtId = districtId;
        this.districtName = districtName;
        this.cityId = cityId;
        this.cityName = cityName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
