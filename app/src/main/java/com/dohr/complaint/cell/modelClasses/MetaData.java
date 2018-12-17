package com.dohr.complaint.cell.modelClasses;

import com.squareup.moshi.Json;

public class MetaData {

    @Json(name = "name")
    private String name;
    @Json(name = "mobile_no")
    private String mobileNo;
    @Json(name = "cnic")
    private String cnic;
    @Json(name = "email")
    private String email;
    @Json(name = "city")
    private String city;
    @Json(name = "api_token")
    private String apiToken;
    @Json(name = "verification_code")
    private String verificationCode;
    @Json(name = "updated_at")
    private String updatedAt;
    @Json(name = "created_at")
    private String createdAt;
    @Json(name = "id")
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MetaData{" +
                "name='" + name + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", cnic='" + cnic + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", apiToken='" + apiToken + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", id=" + id +
                '}';
    }
}
