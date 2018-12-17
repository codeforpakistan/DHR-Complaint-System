package com.dohr.complaint.cell.modelClasses;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.squareup.moshi.Json;

@Entity
public class AllCategory {

    @NonNull
    @PrimaryKey
    @Json(name = "id")
    private String id;

    @Json(name = "cat_name")
    private String catName;
    @Json(name = "cat_slug")
    private String catSlug;
    @Json(name = "level")
    private String level;
    @Json(name = "parent_id")
    private String parentId;
    @Json(name = "created_at")
    private String createdAt;
    @Json(name = "updated_at")
    private String updatedAt;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatSlug() {
        return catSlug;
    }

    public void setCatSlug(String catSlug) {
        this.catSlug = catSlug;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    @Override
    public String toString() {
        return "AllCategory{" +
                "id='" + id + '\'' +
                ", catName='" + catName + '\'' +
                ", catSlug='" + catSlug + '\'' +
                ", level='" + level + '\'' +
                ", parentId='" + parentId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}