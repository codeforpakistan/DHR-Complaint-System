package com.dohr.complaint.cell.modelClasses;

/**
 * Created by HP on 9/24/2018.
 */


import java.util.List;
import com.squareup.moshi.Json;

public class AnnouncementModule {

    @Json(name = "success")
    private String success;
    @Json(name = "status")
    private Integer status;
    @Json(name = "message")
    private String message;
    @Json(name = "announcements")
    private List<Announcement> announcements = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }


    @Override
    public String toString() {
        return "AnnouncementModule{" +
                "success='" + success + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", announcements=" + announcements +
                '}';
    }
}
