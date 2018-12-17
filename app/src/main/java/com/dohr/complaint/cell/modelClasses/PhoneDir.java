package com.dohr.complaint.cell.modelClasses;

/**
 * Created by HP on 9/25/2018.
 */

import com.squareup.moshi.Json;

public class PhoneDir {

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "designation")
    private String designation;
    @Json(name = "office_number")
    private String officeNumber;
    @Json(name = "created_at")
    private Object createdAt;
    @Json(name = "updated_at")
    private Object updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "PhoneDir{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", officeNumber='" + officeNumber + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
