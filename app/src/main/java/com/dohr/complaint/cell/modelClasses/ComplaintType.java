package com.dohr.complaint.cell.modelClasses;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.squareup.moshi.Json;

import java.util.List;

public class ComplaintType {
    @Json(name = "success")
    private String success;
    @Json(name = "status")
    private Integer status;
    @Json(name = "message")
    private String message;
    @Json(name = "All categories")
    private List<AllCategory> allCategories = null;

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

    public List<AllCategory> getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(List<AllCategory> allCategories) {
        this.allCategories = allCategories;
    }

    @Override
    public String toString() {
        return "ComplaintType{" +
                "success='" + success + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", allCategories=" + allCategories +
                '}';
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

}


