package id.smartpesantren.service.dto;

import id.smartpesantren.entity.PersonData;
import id.smartpesantren.entity.WorkingHour;

import java.time.LocalTime;

public class PersonDTO {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String personType;
    private String photo;
    private String jobPosition;
    private String presenceLocation;
    private WorkingHour workingHour;

    public PersonDTO() {
    }

    public PersonDTO(PersonData p) {
        setId(p.getId());
        setName(p.getName());
        setPhone(p.getPhone());
        setEmail(p.getEmail());
        setPersonType(p.getPersonType());
        setJobPosition(p.getJobPosition() == null? null: p.getJobPosition().getName());
        setPresenceLocation(p.getPresenceLocation() == null? null: p.getPresenceLocation().getName());
        setWorkingHour(p.getWorkingHour());
        setPhoto(p.getPhoto());
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

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getPresenceLocation() {
        return presenceLocation;
    }

    public void setPresenceLocation(String presenceLocation) {
        this.presenceLocation = presenceLocation;
    }

    public WorkingHour getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(WorkingHour workingHour) {
        this.workingHour = workingHour;
    }
}
