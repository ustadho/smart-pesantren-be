package id.smartpesantren.dto;

import id.smartpesantren.entity.Organization;

public interface OrganizationTreeDTO {
    public String getId();
    public String getCode();
    public String getName();
    public String getParentId();
    public Integer getLevel();
    public Boolean getActive();
    public Integer getChildCount();
    public String getPath();
    public String getColor();
}
