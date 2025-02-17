package id.smartpesantren.service.dto;

import id.smartpesantren.entity.PersonData;

public class PersonDTO {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String personType;
    private String photo;

    public PersonDTO() {
    }

    public PersonDTO(PersonData p) {
        setId(p.getId());
        setName(p.getName());
        setPhone(p.getPhone());
        setEmail(p.getEmail());
        setPersonType(p.getPersonType());
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

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
