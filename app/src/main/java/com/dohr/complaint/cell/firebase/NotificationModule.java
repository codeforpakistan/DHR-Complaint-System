package com.dohr.complaint.cell.firebase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sumaira on 10/15/2018.
 */
@Entity
public class NotificationModule {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NotificationModule{" +
                "success='" + success + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}

class Data {

    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("sound")
    @Expose
    private String sound;
    @SerializedName("vibrate")
    @Expose
    private Integer vibrate;
    @SerializedName("click_action")
    @Expose
    private String clickAction;
    @SerializedName("complain_data")
    @Expose
    private Data_ complainData;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Integer getVibrate() {
        return vibrate;
    }

    public void setVibrate(Integer vibrate) {
        this.vibrate = vibrate;
    }

    public String getClickAction() {
        return clickAction;
    }

    public void setClickAction(String clickAction) {
        this.clickAction = clickAction;
    }

    public Data_ getComplainData() {
        return complainData;
    }

    public void setComplainData(Data_ complainData) {
        this.complainData = complainData;
    }

    @Override
    public String toString() {
        return "Data{" +
                "to='" + to + '\'' +
                ", body='" + body + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", sound='" + sound + '\'' +
                ", vibrate=" + vibrate +
                ", clickAction='" + clickAction + '\'' +
                ", complainData=" + complainData +
                '}';
    }
}

 class Data_  {
     @SerializedName("complaint_id")
     @Expose
     private Integer complaintId;
     @SerializedName("user_id")
     @Expose
     private Integer userId;

     public Integer getComplaintId() {
         return complaintId;
     }

     public void setComplaintId(Integer complaintId) {
         this.complaintId = complaintId;
     }

     public Integer getUserId() {
         return userId;
     }

     public void setUserId(Integer userId) {
         this.userId = userId;
     }

     @Override
     public String toString() {
         return "ComplainData{" +
                 "complaintId=" + complaintId +
                 ", userId=" + userId +
                 '}';
     }
 }
