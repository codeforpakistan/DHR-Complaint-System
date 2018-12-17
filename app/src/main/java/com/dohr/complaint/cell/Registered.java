package com.dohr.complaint.cell;

import com.squareup.moshi.Json;

public class Registered {


    @Json(name = "success")
    private Integer success;
    @Json(name = "Message")
    private String message;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Registered{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }


   /* @Json(name = "success")
    private Integer success;
    @Json(name = "Message")
    private String message;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Registered{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }*/
}
