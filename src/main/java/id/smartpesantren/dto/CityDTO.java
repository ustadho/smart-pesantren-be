package id.smartpesantren.dto;

import id.smartpesantren.entity.City;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CityDTO {
    private Integer id;
    @NotNull
    private Integer provinceId;
    private String provinceName;
    @NotNull @NotEmpty
    private String code;
    @NotNull @NotEmpty
    private String name;
    private String description;

    public CityDTO() {
    }

    public CityDTO(City c) {
        this(c.getId(), c.getProvince().getId(), c.getProvince().getName(), c.getCode(), c.getName(), c.getDescription());
    }

    public CityDTO(Integer id, Integer provinceId, String provinceName, String code, String name, String description) {
        this.id = id;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
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
