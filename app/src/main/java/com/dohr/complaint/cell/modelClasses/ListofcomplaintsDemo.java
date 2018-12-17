package com.dohr.complaint.cell.modelClasses;

import java.util.List;
import com.squareup.moshi.Json;

public class ListofcomplaintsDemo {

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

    @Override
    public String toString() {
        return "ListofcomplaintsDemo{" +
                "success='" + success + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", complaintDetail=" + complaintDetail +
                '}';
    }
}