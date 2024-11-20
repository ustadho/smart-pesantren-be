package id.smartpesantren.dto;

import id.smartpesantren.entity.PersonData;

public class GuardianDTO {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String sex;

    public GuardianDTO() {
    }

    public GuardianDTO(PersonData p) {
        setId(p.getId());
        setName(p.getName());
        setAddress(p.getPermanentAddress());
        setPhone(p.getPhone());
        setSex(p.getSex());
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
