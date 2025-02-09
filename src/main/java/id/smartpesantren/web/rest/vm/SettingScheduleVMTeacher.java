package id.smartpesantren.web.rest.vm;

import javax.validation.constraints.NotNull;

public class SettingScheduleVMTeacher {
    private String id;

    @NotNull
    private String teacherId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
