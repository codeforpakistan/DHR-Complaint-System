package com.dohr.complaint.cell.modelClasses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubAllCat {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Sub Categories")
    @Expose
    private List<SubComplaintModel.SubCategory> subCategories = null;

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

    public List<SubComplaintModel.SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubComplaintModel.SubCategory> subCategories) {
        this.subCategories = subCategories;
    }


    @Override
    public String toString() {
        return "SubAllCat{" +
                "success='" + success + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", subCategories=" + subCategories +
                '}';
    }
}