package id.smartpesantren.dto;

import id.smartpesantren.entity.AbstractAuditingEntity;
import id.smartpesantren.entity.WorkingHour;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class WorkingHourDTO extends AbstractAuditingEntity implements Serializable {
    private String id;
    private String code;
    private String name;
    private String color;
    private String description;
    private Set<WorkingHourDetailDTO> details = new HashSet<>();

    public WorkingHourDTO() {
    }

    public WorkingHourDTO(WorkingHour wh) {
        this.setId(wh.getId());
        this.setName(wh.getName());
        this.setCode(wh.getCode());
        this.setDescription(wh.getDescription());
        this.setColor(wh.getColor());
        wh.getDetails().forEach(w -> {
            WorkingHourDetailDTO d = new WorkingHourDetailDTO();
            d.setId(w.getId());
            d.setWorkingTimeId(w.getWorkingTime().getId());
            d.setWorkingTimeName(w.getWorkingTime().getName());
            d.setCheckInTime(w.getWorkingTime().getCheckInTime());
            d.setCheckOutTime(w.getWorkingTime().getCheckOutTime());
            d.setDayId(w.getDay().getId());
            d.setDayName(w.getDay().getName());
            this.details.add(d);
        });
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<WorkingHourDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(Set<WorkingHourDetailDTO> details) {
        this.details = details;
    }
}
