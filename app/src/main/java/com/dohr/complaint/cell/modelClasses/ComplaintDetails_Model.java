package com.dohr.complaint.cell.modelClasses;

import com.squareup.moshi.Json;

import java.util.List;

public class ComplaintDetails_Model {

    @Json(name = "success")
    private String success;
    @Json(name = "status")
    private String status;
    @Json(name = "message")
    private String message;
    @Json(name = "complaint_detail")
    private List<ComplaintDetail> complaintDetail = null;

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

    public List<ComplaintDetail> getComplaintDetail() {
        return complaintDetail;
    }

    public void setComplaintDetail(List<ComplaintDetail> complaintDetail) {
        this.complaintDetail = complaintDetail;
    }
}
