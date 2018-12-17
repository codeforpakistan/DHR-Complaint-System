package com.dohr.complaint.cell.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPassVerification {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("user")
    @Expose
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
        return "ForgetPassVerification{" +
                "success='" + success + '\'' +
                ", status=" + status +
                ", user=" + user +
                '}';
    }
}
