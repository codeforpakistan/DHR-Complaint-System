package com.dohr.complaint.cell.modelClasses;

import com.squareup.moshi.Json;

public class ForgetPasswordModel {

    @Json(name = "success")
    private String success;
    @Json(name = "status")
    private Integer status;
    @Json(name = "user")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ForgetPasswordModel{" +
                "success='" + success + '\'' +
                ", status=" + status +
                ", user=" + user +
                '}';
    }
}