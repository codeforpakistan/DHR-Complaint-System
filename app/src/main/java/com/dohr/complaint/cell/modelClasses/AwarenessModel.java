package com.dohr.complaint.cell.modelClasses;

import com.squareup.moshi.Json;

import java.util.List;

public class AwarenessModel {

    @Json(name = "success")
    private String success;
    @Json(name = "status")
    private Integer status;
    @Json(name = "message")
    private String message;
    @Json(name = "All categories")
    private List<AwarenessAllCategory> allCategories = null;

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

    public List<AwarenessAllCategory> getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(List<AwarenessAllCategory> allCategories) {
        this.allCategories = allCategories;
    }

    @Override
    public String toString() {
        return "AwarenessModel{" +
                "success='" + success + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", allCategories=" + allCategories +
                '}';
    }
}

