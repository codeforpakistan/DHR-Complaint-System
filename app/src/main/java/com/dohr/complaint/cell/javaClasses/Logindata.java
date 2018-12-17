package com.dohr.complaint.cell.javaClasses;

import java.util.List;

import java.util.List;
import com.squareup.moshi.Json;

public class Logindata {

    @Json(name = "success")
    private String success;
    @Json(name = "status")
    private Integer status;
    @Json(name = "message")
    private String message;
    @Json(name = "user_data")
    private UserData userData;

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

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    @Override
    public String toString() {
        return "Logindata{" +
                "success='" + success + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", userData=" + userData +
                '}';
    }
}