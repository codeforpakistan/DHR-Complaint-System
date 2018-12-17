package com.dohr.complaint.cell.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class postData {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("vechicle_seized_message")
    @Expose
    private String vechicleSeizedMessage;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getVechicleSeizedMessage() {
        return vechicleSeizedMessage;
    }

    public void setVechicleSeizedMessage(String vechicleSeizedMessage) {
        this.vechicleSeizedMessage = vechicleSeizedMessage;
    }

    @Override
    public String toString() {
        return "SeizePostData{" +
                "success=" + success +
                ", vechicleSeizedMessage='" + vechicleSeizedMessage + '\'' +
                '}';
    }
}
