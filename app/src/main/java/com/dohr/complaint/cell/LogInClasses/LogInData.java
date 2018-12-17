package com.dohr.complaint.cell.LogInClasses;

import com.dohr.complaint.cell.javaClasses.UserData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

public class LogInData {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_data")
    @Expose
    private UserData userData;

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

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    @Override
    public String toString() {
        return "LogInData{" +
                "success='" + success + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", userData=" + userData +
                '}';
    }
}