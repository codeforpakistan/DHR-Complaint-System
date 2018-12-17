package com.dohr.complaint.cell.modelClasses;

/**
 * Created by HP on 8/27/2018.
 */


import com.squareup.moshi.Json;

public class  ComplaintDetail {

    @Json(name = "id")
    private String id;
    @Json(name = "user_id")
    private String userId;
    @Json(name = "complaint_type")
    private String complaintType;
    @Json(name = "complaint_id")
    private String complaintId;
    @Json(name = "sub_complaint_type")
    private String subComplaintType;
    @Json(name = "subject")
    private String subject;
    @Json(name = "details")
    private String details;
    @Json(name = "dept_name")
    private String deptName;
    @Json(name = "person_phone_number")
    private String personPhoneNumber;
    @Json(name = "location")
    private String location;
    @Json(name = "person_email")
    private String personEmail;
    @Json(name = "person_address")
    private String personAddress;
    @Json(name = "image")
    private String image;
    @Json(name = "vidicon")
    private String video;
    @Json(name = "audio")
    private String audio;
    @Json(name = "document_file")
    private String documentFile;
    @Json(name = "created_at")
    private String createdAt;
    @Json(name = "updated_at")
    private String updatedAt;
    @Json(name = "complaint_status")
    private String complaintStatus;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getSubComplaintType() {
        return subComplaintType;
    }

    public void setSubComplaintType(String subComplaintType) {
        this.subComplaintType = subComplaintType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public void setPersonPhoneNumber(String personPhoneNumber) {
        this.personPhoneNumber = personPhoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getDocumentFile() {
        return documentFile;
    }

    public void setDocumentFile(String documentFile) {
        this.documentFile = documentFile;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(String complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    @Override
    public String toString() {
        return "ComplaintDetail{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", complaintType='" + complaintType + '\'' +
                ", complaintId='" + complaintId + '\'' +
                ", subComplaintType='" + subComplaintType + '\'' +
                ", subject='" + subject + '\'' +
                ", details='" + details + '\'' +
                ", deptName='" + deptName + '\'' +
                ", personPhoneNumber='" + personPhoneNumber + '\'' +
                ", location='" + location + '\'' +
                ", personEmail='" + personEmail + '\'' +
                ", personAddress='" + personAddress + '\'' +
                ", image='" + image + '\'' +
                ", vidicon='" + video + '\'' +
                ", audio='" + audio + '\'' +
                ", documentFile='" + documentFile + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", complaintStatus='" + complaintStatus + '\'' +
                '}';
    }
}