package com.dohr.complaint.cell.LogInClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

public class UserProfileModle {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserProfileModle{" +
                "success='" + success + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
