package com.dohr.complaint.cell.modelClasses;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComplaintModel {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("All categories")
    @Expose
    private List<AllCategory> allCategories = null;

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

    public List<AllCategory> getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(List<AllCategory> allCategories) {
        this.allCategories = allCategories;
        }

    @Override
    public String toString() {
        return "ComplaintModel{" +
                "success='" + success + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", allCategories=" + allCategories +
                '}';
    }

    @Entity
    public static class AllCategory {


        @SerializedName("id")
        @Expose
        @NonNull
        @PrimaryKey
        private String id;
        @SerializedName("cat_name")
        @Expose
        private String catName;
        @SerializedName("cat_slug")
        @Expose
        private String catSlug;
        @SerializedName("level")
        @Expose
        private String level;
        @SerializedName("parent_id")
        @Expose
        private String parentId;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public AllCategory(@NonNull String id, String catName) {
            this.id = id;
            this.catName = catName;
        }

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
}


