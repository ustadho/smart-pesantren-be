package id.smartpesantren.dto;

import id.smartpesantren.entity.Section;

public class SectionDTO {
    private String id;
    private String code;
    private String name;
    private String description;

    public SectionDTO() {
    }

    public SectionDTO(Section l) {
        this(l.getId(), l.getCode(), l.getName(), l.getDescription());
    }

    public SectionDTO(String id, String code, String name, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
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

}
