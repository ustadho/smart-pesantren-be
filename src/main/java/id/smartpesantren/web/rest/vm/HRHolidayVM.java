package id.smartpesantren.web.rest.vm;

import id.smartpesantren.entity.HRHoliday;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class HRHolidayVM {
    private String id;

    @NotNull
    private Integer holidayId;
    private String holidayName;

    @NotNull
    private LocalDate eventDate;

    private String description;

    public HRHolidayVM() {
    }

    public HRHolidayVM(HRHoliday hh) {
        setId(hh.getId());
        setHolidayId(hh.getHoliday().getId());
        setHolidayName(hh.getHoliday().getName());
        setDescription(hh.getDescription());
        setEventDate(hh.getEventDate());
    }

    public HRHolidayVM(String id, Integer holidayId, String holidayName, LocalDate eventDate, String description) {
        this.id = id;
        this.holidayId = holidayId;
        this.holidayName = holidayName;
        this.eventDate = eventDate;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Integer holidayId) {
        this.holidayId = holidayId;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
