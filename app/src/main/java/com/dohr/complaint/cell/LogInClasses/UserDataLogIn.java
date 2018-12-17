package com.dohr.complaint.cell.LogInClasses;

import com.squareup.moshi.Json;

public class UserDataLogIn {


    @Json(name = "id")
    private Integer id;
    @Json(name = "name")
    private String name;
    @Json(name = "father_name")
    private Object fatherName;
    @Json(name = "gender")
    private Object gender;
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
    @Json(name = "device_token")
    private String deviceToken;
    @Json(name = "verification_code")
    private String verificationCode;
    @Json(name = "created_at")
    private String createdAt;
    @Json(name = "updated_at")
    private String updatedAt;
    @Json(name = "address")
    private Object address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getFatherName() {
        return fatherName;
    }

    public void setFatherName(Object fatherName) {
        this.fatherName = fatherName;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
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

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
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

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserDataLogIn{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fatherName=" + fatherName +
                ", gender=" + gender +
                ", mobileNo='" + mobileNo + '\'' +
                ", cnic='" + cnic + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", apiToken='" + apiToken + '\'' +
                ", deviceToken='" + deviceToken + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", address=" + address +
                '}';
    }
}

