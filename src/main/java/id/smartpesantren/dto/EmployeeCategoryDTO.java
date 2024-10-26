package id.smartpesantren.dto;

import id.smartpesantren.entity.EmployeeCategory;

public class EmployeeCategoryDTO {
    private String id;
    private String code;
    private String name;

    public EmployeeCategoryDTO() {
    }

    public EmployeeCategoryDTO(EmployeeCategory c) {
        this(c.getId(), c.getCode(), c.getName());
    }

    public EmployeeCategoryDTO(String id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
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
}
