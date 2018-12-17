package com.dohr.complaint.cell.firebase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.dohr.complaint.cell.modelClasses.Notification;
import com.squareup.moshi.Json;

/**
 * Created by Sumaira on 11/9/2018.
 */


public class ComplainData {

    @Json(name = "success")
    private String success;
    @Json(name = "status")
    private Integer status;
    @Json(name = "message")
    private String message;
    @Json(name = "notification")
    private Notification notification;

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

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }



    @Override
    public String toString() {
        return "ComplainData{" +
                "success='" + success + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", notification=" + notification +
                '}';
    }



}



