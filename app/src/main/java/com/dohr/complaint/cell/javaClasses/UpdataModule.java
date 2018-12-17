package com.dohr.complaint.cell.javaClasses;

import com.dohr.complaint.cell.modelClasses.User;
import com.squareup.moshi.Json;

class UpdataModule {
    @Json(name = "success")
    private String success;
    @Json(name = "status")
    private String status;
    @Json(name = "message")
    private String message;
    @Json(name = "user")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UpdataModule{" +
                "success='" + success + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", user=" + user +
                '}';
    }
}
