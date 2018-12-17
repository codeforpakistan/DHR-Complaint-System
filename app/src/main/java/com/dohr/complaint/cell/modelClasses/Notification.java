package com.dohr.complaint.cell.modelClasses;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.squareup.moshi.Json;

@Entity
public class Notification {
    @PrimaryKey
    @NonNull
    @Json(name = "id")
    private int id;
    @Json(name = "msg")
    private String msg;
    @Json(name = "title")
    private String title;
    private String data;
    private String status;



    public Notification(int id, String msg, String title,String data,String status) {
        this.msg = msg;
        this.title = title;
        this.id = id;
        this.data=data;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", title='" + title + '\'' +
                ", data='" + data + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
