package id.smartpesantren.web.rest.vm;

import java.math.BigDecimal;

public class CardTypeVM {
    private String id;
    private String name;
    private String note;
    private Short type;
    private BigDecimal charge;
    private Boolean suspended;

    public CardTypeVM() {
    }

    public CardTypeVM(String id, String name, String note, Short type, BigDecimal charge, Boolean suspended) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.type = type;
        this.charge = charge;
        this.suspended = suspended;
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

    public String getNote() {
        return note;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }
}
