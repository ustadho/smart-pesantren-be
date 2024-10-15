package id.smartpesantren.dto;

import id.smartpesantren.entity.City;
import id.smartpesantren.entity.District;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DistrictDTO {
    private Integer id;
    @NotNull
    private Integer cityId;
    private String cityName;
    @NotNull @NotEmpty
    private String code;
    @NotNull @NotEmpty
    private String name;
    private String description;

    public DistrictDTO() {
    }

    public DistrictDTO(District c) {
        this(c.getId(), c.getCity().getId(), c.getCity().getName(), c.getCode(), c.getName(), c.getDescription());
    }

    public DistrictDTO(Integer id, Integer cityId, String cityName, String code, String name, String description) {
        this.id = id;
        this.cityId = cityId;
        this.cityName = cityName;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
