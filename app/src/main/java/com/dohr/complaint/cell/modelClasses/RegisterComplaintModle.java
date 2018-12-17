package com.dohr.complaint.cell.modelClasses;

/**
 * Created by HP on 8/27/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

public class RegisterComplaintModle {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

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

    @Override
    public String toString() {
        return "RegisterComplaintModle{" +
                "success='" + success + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
