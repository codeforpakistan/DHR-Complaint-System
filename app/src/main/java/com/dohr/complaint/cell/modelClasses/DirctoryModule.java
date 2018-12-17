package com.dohr.complaint.cell.modelClasses;

/**
 * Created by HP on 9/25/2018.
 */

import java.util.List;
import com.squareup.moshi.Json;

public class DirctoryModule {

    @Json(name = "success")
    private String success;
    @Json(name = "status")
    private Integer status;
    @Json(name = "message")
    private String message;
    @Json(name = "phone_dir")
    private List<PhoneDir> phoneDir = null;

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

    public List<PhoneDir> getPhoneDir() {
        return phoneDir;
    }

    public void setPhoneDir(List<PhoneDir> phoneDir) {
        this.phoneDir = phoneDir;
    }

    @Override
    public String toString() {
        return "DirctoryModule{" +
                "success='" + success + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", phoneDir=" + phoneDir +
                '}';
    }
}
