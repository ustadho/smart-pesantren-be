package id.smartpesantren.dto;

import id.smartpesantren.constant.Sex;
import id.smartpesantren.entity.PersonData;

import java.util.Date;

public class GuardianDTO {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Date dob;
    private String sex;
    private String title;

    public GuardianDTO() {
    }

    public GuardianDTO(PersonData p) {
        setId(p.getId());
        setName(p.getName());
        String address = p.getPermanentAddress()==null? "": p.getPermanentAddress();
        if(p.getPermanentSubDistrict() != null) {
//            address += " - "+ p.getPermanentSubDistrict().getName()+" - "+ p.getPermanentSubDistrict().getDistrict().getName()+" - "+ p.getPermanentSubDistrict().getDistrict().getCity().getName();
            address += " - "+ p.getPermanentSubDistrict().getDistrict().getCity().getName();
        }
        setAddress(address);
        setPhone(p.getPhone());
        setEmail(p.getEmail());
        setSex(p.getSex().equalsIgnoreCase(Sex.MALE)? "Laki-laki": "Perempuan");
        setDob(p.getDob());
        setTitle(p.getPersonTitle() == null? null: p.getPersonTitle().getName());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
