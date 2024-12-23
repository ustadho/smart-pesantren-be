package id.smartpesantren.dto;

import id.smartpesantren.web.rest.vm.SubjectScheduleVM;

import java.util.List;

public class SubjectScheduleDTO {
    private ActivityTimeDTO activityTime;
    private List<SubjectScheduleVM> details;

    public ActivityTimeDTO getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(ActivityTimeDTO activityTime) {
        this.activityTime = activityTime;
    }

    public List<SubjectScheduleVM> getDetails() {
        return details;
    }

    public void setDetails(List<SubjectScheduleVM> details) {
        this.details = details;
    }
}
