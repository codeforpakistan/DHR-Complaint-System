package com.dohr.complaint.cell.modelClasses;

/**
 * Created by HP on 8/27/2018.
 */

import com.squareup.moshi.Json;

public class GetRegComplaintModle {

    @Json(name = "success")
    private String success;
    @Json(name = "status")
    private String status;
    @Json(name = "message")
    private String message;
    @Json(name = "complaint_detail")
    private ComplaintDetail complaintDetail;

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

    public ComplaintDetail getComplaintDetail() {
        return complaintDetail;
    }

    public void setComplaintDetail(ComplaintDetail complaintDetail) {
        this.complaintDetail = complaintDetail;
    }

    @Override
    public String toString() {
        return "GetRegComplaintModle{" +
                "success='" + success + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", complaintDetail=" + complaintDetail +
                '}';
    }
}
