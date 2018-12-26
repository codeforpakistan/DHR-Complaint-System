package com.dohr.complaint.cell.modelClasses;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
@Entity
public class NotificationModel {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    String complaint_id;
    String user_id;
    String title;
    String status;
    String date;
    String body;

    public NotificationModel(String complaint_id, String user_id, String title, String status, String date, String body) {
        this.complaint_id = complaint_id;
        this.user_id = user_id;
        this.title = title;
        this.status = status;
        this.date = date;
        this.body = body;
    }

    public String getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(String complaint_id) {
        this.complaint_id = complaint_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;

    }

    @Override
    public String toString() {
        return "NotificationModel{" +
                "complaint_id='" + complaint_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
